package org.hell.homework10.repository;

import org.hell.homework10.model.Genre;
import org.springframework.data.repository.CrudRepository;

public interface GenreRepository extends CrudRepository<Genre, Long> {

    Genre findByName(String name);

}
