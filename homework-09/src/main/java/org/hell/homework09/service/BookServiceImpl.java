package org.hell.homework09.service;

import lombok.AllArgsConstructor;
import org.hell.homework09.model.Author;
import org.hell.homework09.model.Genre;
import org.hell.homework09.repository.BookRepository;
import org.hell.homework09.model.Book;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@AllArgsConstructor
@Service
public class BookServiceImpl implements BookService {

    private final BookRepository repository;
    private final AuthorServiceImpl authorService;
    private final GenreServiceImpl genreService;

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
    public Book update(Book book) {
        return repository.save(book);
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
