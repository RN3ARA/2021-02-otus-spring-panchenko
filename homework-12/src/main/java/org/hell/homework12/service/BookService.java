package org.hell.homework12.service;

import org.hell.homework12.model.Book;

import java.util.List;

public interface BookService {

    Book findById(long id);

    List<Book> findAll();

    void deleteById(long id);

    Book save(Book book);

    Book update(Book book);

    long count();

}
