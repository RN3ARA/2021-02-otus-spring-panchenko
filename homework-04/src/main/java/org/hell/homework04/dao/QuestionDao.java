package org.hell.homework04.dao;

import org.hell.homework04.domain.Question;

import java.util.List;

public interface QuestionDao {

    List<Question> findAll();
}
