package org.hell.homework07.repository;

import org.hell.homework07.model.Genre;

import java.util.List;

public interface GenreRepository {

    Genre findByName(String name);
    List<Genre> findAll();

}
