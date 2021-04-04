package org.hell.homework06.repository;

import org.hell.homework06.model.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreRepositoryJpa {

    Genre save(Genre genre);

    Optional<Genre> findById(long id);

    Genre findByName(String name);
    List<Genre> findAll();

    void deleteById(long id);

    long count();
}
