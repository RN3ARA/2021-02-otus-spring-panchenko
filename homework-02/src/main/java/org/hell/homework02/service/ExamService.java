package org.hell.homework02.service;

import org.hell.homework02.domain.Question;

import java.util.List;

public interface ExamService {
    int calculateResults();
    void getStudentInfo();
    void start();
    void showResults(int score);
}
