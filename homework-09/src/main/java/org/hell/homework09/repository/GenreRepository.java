package org.hell.homework09.repository;

import org.hell.homework09.model.Genre;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface GenreRepository extends CrudRepository<Genre, Long> {

    Genre findByName(String name);
    List<Genre> findAll();

}
