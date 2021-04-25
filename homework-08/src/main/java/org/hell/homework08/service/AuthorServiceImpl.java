package org.hell.homework08.service;

import org.hell.homework08.model.Author;
import org.hell.homework08.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final BookRepository bookRepository;

    public AuthorServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Author> findAllAuthors() {
        return bookRepository.findAllAuthors();
    }

    @Override
    public List<Author> findBookAuthors(String bookId) {
        return bookRepository.findBookAuthors(bookId);
    }

    @Override
    public Author findById(String authorId) {
        return bookRepository.findAuthorById(authorId);
    }
}
