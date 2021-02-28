package org.hell.homework01.dao;

import org.hell.homework01.domain.Question;

import java.util.List;

public interface QuestionDao {

    List<Question> findAll();
}
