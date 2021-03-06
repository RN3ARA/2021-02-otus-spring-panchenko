package org.hell.homework05.dao;

import org.hell.homework05.domain.Book;

import java.util.List;

public interface BookDao {

    long insert(Book book);

    Book getById(long id);

    List<Book> getAll();

    void update(Book book);

    void deleteById(long id);

    int count();
}
