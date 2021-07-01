package org.hell.homework12.repository;

import org.hell.homework12.model.Genre;
import org.springframework.data.repository.CrudRepository;

public interface GenreRepository extends CrudRepository<Genre, Long> {

    Genre findByName(String name);

}
