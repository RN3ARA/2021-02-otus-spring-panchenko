package org.hell.homework06.dao;

import org.hell.homework06.domain.Author;

import java.util.List;

public interface AuthorDao {

    long insert(Author author);

    Author getById(long id);

    Author getByFullName(String firstName, String lastName);

    List<Author> getAll();

    void update(Author author);

    void deleteById(long id);

    int count();
}
