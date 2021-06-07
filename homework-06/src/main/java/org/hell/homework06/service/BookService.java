package org.hell.homework06.service;

import org.hell.homework06.model.Book;
import org.hell.homework06.model.Comment;

import java.util.List;

public interface BookService {

    Book findById(long id);

    List<Book> findAll();

    void deleteById(long id);

    Book save(Book book);

    void update(Book book);

    long count();

}
