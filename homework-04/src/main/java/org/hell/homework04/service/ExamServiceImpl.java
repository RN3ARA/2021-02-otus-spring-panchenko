package org.hell.homework04.service;

import org.hell.homework04.config.AppProps;
import org.hell.homework04.dao.QuestionDao;
import org.hell.homework04.domain.Answer;
import org.hell.homework04.domain.Question;
import org.hell.homework04.domain.Student;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExamServiceImpl implements ExamService {

    private final QuestionDao dao;
    private final IOService ioService;
    private final Student student;
    private final MessageSource messageSource;
    private final AppProps appProps;
    private String firstNameMsg;
    private String lastNameMsg;
    private String scoreMsg;

    public ExamServiceImpl(QuestionDao dao, IOService ioService, MessageSource messageSource, AppProps appProps) {
        this.dao = dao;
        this.ioService = ioService;
        this.appProps = appProps;
        this.student = new Student();
        this.messageSource = messageSource;
        initMessages();
    }

    @Override
    public void start() {
        getStudentInfo();
        List<Question> questions = dao.findAll();
        for (int i = 0; i < questions.size(); i++) {
            Question question = questions.get(i);
            if (!appProps.getLocale().getLanguage().equals("en")) {
                ioService.writeMessage(messageSource.getMessage(String.format("questions.question%d", i), null, appProps.getLocale()));
            } else {
                ioService.writeMessage(question.getText());
            }
            List<Answer> answers = question.getAnswers();
            for (int j = 0; j < answers.size(); j++) {
                Answer answer = answers.get(j);
                if (!appProps.getLocale().getLanguage().equals("en")) {
                    ioService.writeMessage(messageSource.getMessage(String.format("questions.question%d.answer%d", i, j), null, appProps.getLocale()));
                } else {
                    String text = answer.getText();
                    ioService.writeMessage(text);
                }
            }
            student.getAnswers().add(ioService.readInt());
        }
        showResults(calculateResults());
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
        ioService.writeMessage(firstNameMsg);
        student.setFirstName(ioService.readString());
        ioService.writeMessage(lastNameMsg);
        student.setLastName(ioService.readString());
    }

    private void showResults(int score) {
        ioService.writeMessage(String.format("%s %d", scoreMsg, score));
    }
}
