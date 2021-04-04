package org.hell.homework06.repository;

import org.hell.homework06.model.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepositoryJpa {

    Book save(Book book);

    Optional<Book> findById(long id);

    List<Book> findAll();

    void deleteById(long id);

    long count();
}
