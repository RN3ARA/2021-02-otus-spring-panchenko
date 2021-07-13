package org.hell.homework17.repository;

import org.hell.homework17.model.Genre;
import org.springframework.data.repository.CrudRepository;

public interface GenreRepository extends CrudRepository<Genre, Long> {

    Genre findByName(String name);

}
