package org.hell.homework02.service;

import org.hell.homework02.dao.QuestionDao;
import org.hell.homework02.domain.Question;

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
