package org.hell.homework06.service;

import org.hell.homework06.model.Author;
import org.hell.homework06.model.Genre;
import org.hell.homework06.repository.BookRepositoryJpa;
import org.hell.homework06.model.Book;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BookService {

    private final BookRepositoryJpa repository;
    private final AuthorService authorService;
    private final GenreService genreService;

    public BookService(BookRepositoryJpa repository, AuthorService authorService, GenreService genreService) {
        this.repository = repository;
        this.authorService = authorService;
        this.genreService = genreService;
    }

    @Transactional(readOnly = true)
    public Book getById(long id) {
        return repository.getById(id)
                .orElse(null);
    }

    @Transactional(readOnly = true)
    public List<Book> getAll() {
        return repository.getAll();
    }

    @Transactional
    public void deleteById(long id) {
        repository.deleteById(id);
    }

    @Transactional
    public Book insert(Book book) {
        checkForAuthor(book);
        checkForGenre(book);
        return repository.insert(book);
    }

    @Transactional
    public void update(Book book) {
        repository.update(book);
    }

    @Transactional(readOnly = true)
    public long count() {
        return repository.count();
    }

    private void checkForGenre(Book book) {
        Genre genre = genreService.getByName(book.getGenre().getName());
        if (genre == null) {
            book.getGenre().setId(genreService.insert(book.getGenre()).getId());
        } else {
            book.getGenre().setId(genre.getId());
        }
    }

    private void checkForAuthor(Book book) {
        Author author = authorService.getByFullName(
                book.getAuthor().getFirstName(), book.getAuthor().getLastName());
        if (author == null) {
            book.getAuthor().setId(authorService.insert(book.getAuthor()).getId());
        } else {
            book.getAuthor().setId(author.getId());
        }
    }

}
