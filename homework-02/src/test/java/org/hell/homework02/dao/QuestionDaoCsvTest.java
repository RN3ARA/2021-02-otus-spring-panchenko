package org.hell.homework02.dao;

import org.hell.homework02.domain.Answer;
import org.hell.homework02.domain.Question;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;

import java.util.List;

class QuestionDaoCsvTest {
    private static QuestionDaoCsv dao;

    @BeforeAll
    static void init() {
        dao = new QuestionDaoCsv(new ClassPathResource("questions.csv"));
    }

    @Test
    void countOfQuestionsMatchesTest() {
        List<Question> questions = dao.findAll();
        Assertions.assertEquals(5, questions.size());
    }

    @Test
    void countOfAnswersMatchesTest() {
        List<Answer> answers = dao.findAll()
                .get(0).getAnswers();
        Assertions.assertEquals(4, answers.size());
    }

}