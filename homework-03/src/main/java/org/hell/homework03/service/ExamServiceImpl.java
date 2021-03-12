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
import java.util.stream.IntStream;

@Service
public class ExamServiceImpl implements ExamService {

    private final QuestionDao dao;
    private final IOService service;
    private final Student student;
    private final MessageSource messageSource;
    private final AppProps appProps;
    private String firstNameMsg;
    private String lastNameMsg;
    private String scoreMsg;

    public ExamServiceImpl(QuestionDao dao, IOService service, MessageSource messageSource, AppProps appProps) {
        this.dao = dao;
        this.service = service;
        this.appProps = appProps;
        this.student = new Student();
        this.messageSource = messageSource;
        initMessages();
    }

    private void initMessages() {
        firstNameMsg = messageSource.getMessage("messages.firstName", null, appProps.getLocale());
        lastNameMsg = messageSource.getMessage("messages.lastName", null, appProps.getLocale());
        scoreMsg = messageSource.getMessage("messages.score", null, appProps.getLocale());
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
        service.writeMessage(firstNameMsg);
        student.setFirstName(service.readString());
        service.writeMessage(lastNameMsg);
        student.setLastName(service.readString());
    }

    @Override
    public void start() {
        getStudentInfo();
        List<Question> questions = dao.findAll();
        for (int i = 0; i < questions.size(); i++) {
            Question question = questions.get(i);
            if (!appProps.getLocale().getLanguage().equals("en")) {
                service.writeMessage(messageSource.getMessage(String.format("questions.question%d", i), null, appProps.getLocale()));
            } else {
                service.writeMessage(question.getText());
            }
            List<Answer> answers = question.getAnswers();
            for (int j = 0; j < answers.size(); j++) {
                Answer answer = answers.get(j);
                if (!appProps.getLocale().getLanguage().equals("en")) {
                    service.writeMessage(messageSource.getMessage(String.format("questions.question%d.answer%d", i, j), null, appProps.getLocale()));
                } else {
                    String text = answer.getText();
                    service.writeMessage(text);
                }
            }
            student.getAnswers().add(service.readInt());
        }
        showResults(calculateResults());
    }

    private void showResults(int score) {
        service.writeMessage(String.format("%s %d", scoreMsg, score));
    }
}
