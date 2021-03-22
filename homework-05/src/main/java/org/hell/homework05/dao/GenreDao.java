package org.hell.homework05.dao;

import org.hell.homework05.domain.Genre;

import java.util.List;

public interface GenreDao {

    void insert(Genre genre);

    Genre getById(long id);

    List<Genre> getAll();

    void update(Genre genre);

    void deleteById(long id);

    int count();
}
