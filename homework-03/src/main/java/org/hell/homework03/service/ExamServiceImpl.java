package org.hell.homework03.service;

import org.hell.homework03.dao.QuestionDao;
import org.hell.homework03.domain.Answer;
import org.hell.homework03.domain.Question;
import org.hell.homework03.domain.Student;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExamServiceImpl implements ExamService {

    private final QuestionDao dao;
    private final IOService service;
    private final Student student;

    public ExamServiceImpl(QuestionDao dao, IOService service) {
        this.dao = dao;
        this.service = service;
        this.student = new Student();
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
        service.writeMessage("First Name:");
        student.setFirstName(service.readString());
        service.writeMessage("Last Name:");
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
        service.writeMessage(String.format("Your score is %s.", score));
    }
}
