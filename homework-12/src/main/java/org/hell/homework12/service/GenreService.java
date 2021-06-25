package org.hell.homework12.service;

import org.hell.homework12.model.Genre;

import java.util.List;

public interface GenreService {

    Genre findById(long id);

    Genre findByName(String name);

    List<Genre> findAll();

    void deleteById(long id);

    Genre save(Genre genre);

    void update(Genre genre);

    long count();
}
