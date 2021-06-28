package org.hell.homework16.service;

import org.hell.homework16.model.Author;

import java.util.List;

public interface AuthorService {
    Author findById(long id);

    Author findByFullName(String firstName, String lastName);

    List<Author> findAll();

    void deleteById(long id);

    Author save(Author author);

    void update(Author author);

    long count();
}
