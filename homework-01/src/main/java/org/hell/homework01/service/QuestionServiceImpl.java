package org.hell.homework01.service;

import org.hell.homework01.dao.QuestionDao;

public class QuestionServiceImpl implements QuestionService {
    private final QuestionDao dao;

    public QuestionServiceImpl(QuestionDao dao) {
        this.dao = dao;
    }

}
