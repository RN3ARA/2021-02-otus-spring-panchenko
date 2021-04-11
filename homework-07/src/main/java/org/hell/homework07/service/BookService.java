package org.hell.homework07.service;

import org.hell.homework07.model.Book;

import java.util.List;

public interface BookService {

    Book findById(long id);

    List<Book> findAll();

    void deleteById(long id);

    Book save(Book book);

    void update(Book book);

    long count();

}
