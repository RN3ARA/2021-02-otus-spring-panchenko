package org.hell.homework06.repository;

import org.hell.homework06.model.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreRepositoryJpa {

    Genre insert(Genre genre);

    Optional<Genre> getById(long id);

    Genre getByName(String name);
    List<Genre> getAll();

    void update(Genre genre);

    void deleteById(long id);

    long count();
}
