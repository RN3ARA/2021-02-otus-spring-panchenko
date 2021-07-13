package org.hell.homework17.repository;

import org.hell.homework17.model.Author;
import org.springframework.data.repository.CrudRepository;

public interface AuthorRepository extends CrudRepository<Author, Long> {

    Author findByFirstNameAndLastName(String firstName, String lastName);

}
