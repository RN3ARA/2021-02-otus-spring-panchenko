package org.hell.homework10.repository;

import org.hell.homework10.model.Author;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AuthorRepository extends CrudRepository<Author, Long> {

    Author findByFirstNameAndLastName(String firstName, String lastName);

    List<Author> findAll();

}
