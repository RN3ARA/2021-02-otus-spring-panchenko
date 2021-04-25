package org.hell.homework08.service;

import org.hell.homework08.model.Author;
import org.hell.homework08.model.Book;
import org.hell.homework08.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository repository;

    public BookServiceImpl(BookRepository repository) {
        this.repository = repository;
    }

    @Override
    public Book findById(String id) {
        return repository.findById(id)
                .orElse(null);
    }

    @Override
    public List<Book> findAll() {
        return repository.findAll();
    }

    @Override
    public void deleteById(String id) {
        repository.deleteById(id);
    }

    @Override
    public Book save(Book book) {
        return repository.insert(book);
    }

    @Override
    public void update(Book book) {
        repository.save(book);
    }

    @Override
    public long count() {
        return repository.count();
    }

}
