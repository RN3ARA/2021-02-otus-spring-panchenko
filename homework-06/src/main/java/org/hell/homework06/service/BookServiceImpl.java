package org.hell.homework06.service;

import org.hell.homework06.model.Author;
import org.hell.homework06.model.Genre;
import org.hell.homework06.repository.BookRepositoryJpa;
import org.hell.homework06.model.Book;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepositoryJpa repository;
    private final AuthorServiceImpl authorService;
    private final GenreServiceImpl genreService;

    public BookServiceImpl(BookRepositoryJpa repository, AuthorServiceImpl authorService, GenreServiceImpl genreService) {
        this.repository = repository;
        this.authorService = authorService;
        this.genreService = genreService;
    }

    @Override
    public Book findById(long id) {
        return repository.findById(id)
                .orElse(null);
    }

    @Override
    public List<Book> findAll() {
        return repository.findAll();
    }

    @Override
    @Transactional
    public void deleteById(long id) {
        repository.deleteById(id);
    }

    @Override
    @Transactional
    public Book save(Book book) {
        checkForAuthor(book);
        checkForGenre(book);
        return repository.save(book);
    }

    @Override
    @Transactional
    public void update(Book book) {
        repository.save(book);
    }

    @Override
    public long count() {
        return repository.count();
    }

    private void checkForGenre(Book book) {
        Genre genre = genreService.findByName(book.getGenre().getName());
        if (genre == null) {
            book.getGenre().setId(genreService.save(book.getGenre()).getId());
        } else {
            book.getGenre().setId(genre.getId());
        }
    }

    private void checkForAuthor(Book book) {
        Author author = authorService.findByFullName(
                book.getAuthor().getFirstName(), book.getAuthor().getLastName());
        if (author == null) {
            book.getAuthor().setId(authorService.save(book.getAuthor()).getId());
        } else {
            book.getAuthor().setId(author.getId());
        }
    }

}
