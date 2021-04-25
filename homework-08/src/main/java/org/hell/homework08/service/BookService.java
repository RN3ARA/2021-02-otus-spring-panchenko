package org.hell.homework08.service;

import org.hell.homework08.model.Author;
import org.hell.homework08.model.Book;

import java.util.List;

public interface BookService {

    Book findById(String id);

    List<Book> findAll();

    void deleteById(String id);

    Book save(Book book);

    void update(Book book);

    long count();

}
