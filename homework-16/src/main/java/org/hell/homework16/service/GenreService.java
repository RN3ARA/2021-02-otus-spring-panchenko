package org.hell.homework16.service;

import org.hell.homework16.model.Genre;

public interface GenreService {

    Genre findById(long id);

    Genre findByName(String name);

    Iterable<Genre> findAll();

    void deleteById(long id);

    Genre save(Genre genre);

    void update(Genre genre);

    long count();
}
