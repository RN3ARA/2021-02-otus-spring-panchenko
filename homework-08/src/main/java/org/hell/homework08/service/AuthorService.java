package org.hell.homework08.service;

import org.hell.homework08.model.Author;

import java.util.List;

public interface AuthorService {

    List<Author> findAllAuthors();

    List<Author> findBookAuthors(String bookId);

    Author findById(String id);
}
