package org.hell.homework07.repository;

import org.hell.homework07.model.Author;

import java.util.List;

public interface AuthorRepository {

    Author findByFirstNameAndLastName(String firstName, String lastName);

    List<Author> findAll();

}
