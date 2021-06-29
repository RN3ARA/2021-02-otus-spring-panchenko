package org.hell.homework12.service;

import org.hell.homework12.model.Genre;

public interface GenreService {

    Genre findById(long id);

    Genre findByName(String name);

    Iterable<Genre> findAll();

    void deleteById(long id);

    Genre save(Genre genre);

    void update(Genre genre);

    long count();
}
