package org.hell.homework06.repository;

import org.hell.homework06.model.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepositoryJpa {

    Book insert(Book book);

    Optional<Book> getById(long id);

    List<Book> getAll();

    void update(Book book);

    void deleteById(long id);

    long count();
}
