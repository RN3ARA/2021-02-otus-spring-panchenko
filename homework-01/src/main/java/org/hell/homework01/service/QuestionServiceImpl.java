package org.hell.homework01.service;

import org.hell.homework01.dao.QuestionDao;
import org.hell.homework01.domain.Question;

import java.util.List;

public class QuestionServiceImpl implements QuestionService {
    private final QuestionDao dao;

    public QuestionServiceImpl(QuestionDao dao) {
        this.dao = dao;
    }

    @Override
    public List<Question> getAll() {
        return dao.findAll();
    }
}
