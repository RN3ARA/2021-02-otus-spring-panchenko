package org.hell.homework04.service;

import org.hell.homework04.config.AppProps;
import org.hell.homework04.domain.Question;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class CsvParserTest {
    private static final int QUESTION_COUNT = 5;

    @Autowired
    private CsvParser csvParser;

    @Test
    void readCsvResource() {
        List<Question> questionList = csvParser.readCsvResource();
        Assertions.assertEquals(QUESTION_COUNT, questionList.size());
    }
}