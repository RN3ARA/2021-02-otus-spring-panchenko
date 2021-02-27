package org.hell.homework01.dao;

import org.hell.homework01.domain.Question;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class QuestionDaoCsvTest {

    // Test fails with NPE because of missing beans. I don't know if it is necessary to create test Spring context or something else.
    @Test
    void countOfQuestionsMatchesTest() {
        QuestionDaoCsv dao = new QuestionDaoCsv();
        List<Question> questions = dao.findAll();
        Assertions.assertEquals(5, questions.size());
    }
}