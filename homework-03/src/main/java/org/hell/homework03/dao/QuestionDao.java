package org.hell.homework03.dao;

import org.hell.homework03.domain.Question;

import java.util.List;

public interface QuestionDao {

    List<Question> findAll();
}
