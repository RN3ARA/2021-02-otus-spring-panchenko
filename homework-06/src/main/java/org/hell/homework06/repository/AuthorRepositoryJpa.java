package org.hell.homework06.repository;

import org.hell.homework06.model.Author;

import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

public interface AuthorRepositoryJpa {

    Author insert(Author author);

    Optional<Author> getById(long id);

    Author getByFullName(String firstName, String lastName);

    List<Author> getAll();

    void update(Author author);

    void deleteById(long id);

    long count();
}
