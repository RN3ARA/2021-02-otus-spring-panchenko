package org.hell.homework10.repository;

import org.hell.homework10.model.Author;
import org.springframework.data.repository.CrudRepository;

public interface AuthorRepository extends CrudRepository<Author, Long> {

    Author findByFirstNameAndLastName(String firstName, String lastName);

}
