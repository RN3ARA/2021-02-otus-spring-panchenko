package org.hell.homework02.dao;

import org.hell.homework02.domain.Question;

import java.util.List;

public interface QuestionDao {

    List<Question> findAll();
}
