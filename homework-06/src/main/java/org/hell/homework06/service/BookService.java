package org.hell.homework06.service;

import org.hell.homework06.dao.BookDao;
import org.hell.homework06.domain.Book;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookDao dao;

    public BookService(BookDao dao) {
        this.dao = dao;
    }

    public Book getById(long id) {
        return dao.getById(id);
    }

    public List<Book> getAll() {
        return dao.getAll();
    }

    public void deleteById(long id) {
        dao.deleteById(id);
    }

    public long insert(Book book) {
        return dao.insert(book);
    }

    public void update(Book book) {
        dao.update(book);
    }

    public int count() {
        return dao.count();
    }
}
