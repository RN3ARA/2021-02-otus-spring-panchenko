package org.hell.homework16.repository;

import org.hell.homework16.model.Genre;
import org.springframework.data.repository.CrudRepository;

public interface GenreRepository extends CrudRepository<Genre, Long> {

    Genre findByName(String name);

}
