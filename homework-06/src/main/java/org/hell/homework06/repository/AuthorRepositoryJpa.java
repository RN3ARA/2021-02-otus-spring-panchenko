package org.hell.homework06.repository;

import org.hell.homework06.model.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorRepositoryJpa {

    Author save(Author author);

    Optional<Author> findById(long id);

    Author findByFullName(String firstName, String lastName);

    List<Author> findAll();

    void update(Author author);

    void deleteById(long id);

    long count();
}
