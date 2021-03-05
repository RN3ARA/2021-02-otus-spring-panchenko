package org.hell.homework02.service;

import org.hell.homework02.dao.QuestionDao;
import org.hell.homework02.domain.Answer;
import org.hell.homework02.domain.Question;
import org.hell.homework02.domain.Student;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExamServiceImpl implements ExamService {

    //Helper methods for console reading/writing
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    private void writeMessage(String message) {
        System.out.println(message);
    }

    private String readString() {
        String message;
        while (true) {
            try {
                message = reader.readLine();
                break;
            } catch (IOException e) {
                System.out.println("I/O Error!");
            }
        }
        return message;
    }

    private int readInt() {
        int i;
        while (true) {
            try {
                i = Integer.parseInt(readString());
                break;
            } catch (NumberFormatException e) {
                System.out.println("I/O Error");
            }
        }
        return i;
    }

    private final QuestionDao dao;
    private Student student;

    public ExamServiceImpl(QuestionDao dao) {
        this.dao = dao;
        student = new Student();
    }

    @Override
    public int calculateResults() {
        int score = 0;
        List<Integer> correctAnswers = dao.findAll().stream()
                .map(Question::getCorrectAnswer)
                .collect(Collectors.toList());
        Iterator<Integer> correctIterator = correctAnswers.iterator();
        Iterator<Integer> choiceIterator = student.getAnswers().iterator();
        while (correctIterator.hasNext() && choiceIterator.hasNext()) {
            if (choiceIterator.next() == correctIterator.next()) {
                score++;
            }
        }
        return score;
    }

    @Override
    public void getStudentInfo() {
        writeMessage("First Name:");
        student.setFirstName(readString());
        writeMessage("Last Name:");
        student.setLastName(readString());
    }

    @Override
    public void start() {
        getStudentInfo();
        List<Question> questions = dao.findAll();
        for (Question question : questions) {
            writeMessage(question.getText());
            question.getAnswers().stream()
                    .map(Answer::getText)
                    .forEach(this::writeMessage);
            student.getAnswers().add(readInt());
        }
        showResults(calculateResults());
    }

    @Override
    public void showResults(int score) {
        writeMessage("Your score is " + String.valueOf(score));
    }
}
