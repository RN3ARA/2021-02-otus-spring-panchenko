package org.hell.homework04.dao;

import org.hell.homework04.domain.Question;
import org.hell.homework04.service.CsvParser;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class QuestionDaoCsv implements QuestionDao{

    private final CsvParser csvParser;

    public QuestionDaoCsv(CsvParser csvParser) {
        this.csvParser = csvParser;
    }

    @Override
    public List<Question> findAll() {
        return csvParser.readCsvResource();
    }

}
