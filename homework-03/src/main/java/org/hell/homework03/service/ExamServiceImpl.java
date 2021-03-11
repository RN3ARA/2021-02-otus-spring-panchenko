package org.hell.homework03.service;

import org.hell.homework03.config.AppProps;
import org.hell.homework03.dao.QuestionDao;
import org.hell.homework03.domain.Answer;
import org.hell.homework03.domain.Question;
import org.hell.homework03.domain.Student;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExamServiceImpl implements ExamService {

    private final QuestionDao dao;
    private final IOService service;
    private final Student student;
    private final MessageSource messageSource;
    private final AppProps appProps;

    public ExamServiceImpl(QuestionDao dao, IOService service, MessageSource messageSource, AppProps appProps) {
        this.dao = dao;
        this.service = service;
        this.appProps = appProps;
        this.student = new Student();
        this.messageSource = messageSource;
    }

    private int calculateResults() {
        //Getting all correct answers
        int score = 0;
        List<Integer> correctAnswers = dao.findAll().stream()
                .map(Question::getCorrectAnswer)
                .collect(Collectors.toList());

        //Getting student's answers and comparing with correct
        Iterator<Integer> correctIterator = correctAnswers.iterator();
        Iterator<Integer> choiceIterator = student.getAnswers().iterator();
        while (correctIterator.hasNext() && choiceIterator.hasNext()) {
            if (choiceIterator.next().equals(correctIterator.next())) {
                score++;
            }
        }
        return score;
    }

    private void getStudentInfo() {
        String firstNameMsg = messageSource.getMessage("firstName", null, appProps.getLocale());
        service.writeMessage(firstNameMsg);
        student.setFirstName(service.readString());
        String lastNameMsg = messageSource.getMessage("lastName", null, appProps.getLocale());
        service.writeMessage(lastNameMsg);
        student.setLastName(service.readString());
    }

    @Override
    public void start() {
        getStudentInfo();
        List<Question> questions = dao.findAll();
        for (Question question : questions) {
            service.writeMessage(question.getText());
            question.getAnswers().stream()
                    .map(Answer::getText)
                    .forEach(service::writeMessage);
            student.getAnswers().add(service.readInt());
        }
        showResults(calculateResults());
    }

    private void showResults(int score) {
        String scoreMsg = messageSource.getMessage("yourScore", null, appProps.getLocale());
        service.writeMessage(scoreMsg + " " + score);
    }
}
