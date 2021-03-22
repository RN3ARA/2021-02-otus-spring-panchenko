package org.hell.homework05.dao;

import org.hell.homework05.domain.Author;

import java.util.List;

public interface AuthorDao {

    void insert(Author author);

    Author getById(long id);

    List<Author> getAll();

    void update(Author author);

    void deleteById(long id);

    int count();
}
