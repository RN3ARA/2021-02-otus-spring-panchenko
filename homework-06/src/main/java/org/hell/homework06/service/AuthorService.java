package org.hell.homework06.service;

import org.hell.homework06.model.Author;
import org.springframework.transaction.annotation.Transactional;

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
