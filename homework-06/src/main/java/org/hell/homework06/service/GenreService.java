package org.hell.homework06.service;

import org.hell.homework06.model.Genre;
import org.springframework.transaction.annotation.Transactional;

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
