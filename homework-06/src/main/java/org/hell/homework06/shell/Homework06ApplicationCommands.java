package org.hell.homework06.shell;

import lombok.RequiredArgsConstructor;
import org.hell.homework06.model.Author;
import org.hell.homework06.model.Book;
import org.hell.homework06.model.Genre;
import org.hell.homework06.service.AuthorService;
import org.hell.homework06.service.BookService;
import org.hell.homework06.service.GenreService;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
@RequiredArgsConstructor
public class Homework06ApplicationCommands {

    private final BookService bookService;
    private final AuthorService authorService;
    private final GenreService genreService;

    @ShellMethod(key = {"insert-book"}, value = "Insert book into table")
    public String insertBook(@ShellOption(defaultValue = "First Name") String authorFirstName,
                             @ShellOption(defaultValue = "Last Name") String authorLastName,
                             @ShellOption(defaultValue = "Untitled") String title,
                             @ShellOption(defaultValue = "Unknown") String genre) {
        long id = bookService.insert(new Book(new Author(authorFirstName, authorLastName), title, new Genre(genre)))
                .getId();
        return String.format("Inserted book with id %d", id);
    }

    @ShellMethod(key = {"delete-book"}, value = "Delete book from table")
    public void deleteBook(@ShellOption(defaultValue = "0") String id) {
        bookService.deleteById(Long.parseLong(id));
    }

    @ShellMethod(key = {"get-book"}, value = "Get book from table by its Id")
    public String getBook(@ShellOption(defaultValue = "0") String id) {
        return bookService.getById(Long.parseLong(id)).toString();
    }

    @ShellMethod(key = {"get-all-books"}, value = "Get all books from table")
    public String getAllBooks() {
        return bookService.getAll().toString();
    }

    @ShellMethod(key = {"update-book"}, value = "Update book in table")
    public String updateBook(@ShellOption(defaultValue = "0") String id,
                             @ShellOption(defaultValue = "Untitled") String title) {
        Book bookForUpdate = bookService.getById(Long.parseLong(id));
        if (bookForUpdate != null) {
            bookForUpdate.setTitle(title);
            bookService.update(bookForUpdate);
            return String.format("Updated book with id %s", id);
        }
        return "Nothing updated.";
    }

    @ShellMethod(key = {"count-books"}, value = "Get count of books in table")
    public String countBooks() {
        return String.valueOf(bookService.count());
    }

    @ShellMethod(key = {"insert-author"}, value = "Insert author into table")
    public String insertAuthor(@ShellOption(defaultValue = "First Name") String firstName,
                             @ShellOption(defaultValue = "Last Name") String lastName) {
        long id = authorService.insert(new Author(firstName, lastName))
                .getId();
        return String.format("Inserted author with id %d", id);
    }

    @ShellMethod(key = {"delete-author"}, value = "Delete author from table")
    public void deleteAuthor(@ShellOption(defaultValue = "0") String id) {
        authorService.deleteById(Long.parseLong(id));
    }

    @ShellMethod(key = {"get-author"}, value = "Get author from table by its Id")
    public String getAuthor(@ShellOption(defaultValue = "0") String id) {
        return authorService.getById(Long.parseLong(id)).toString();
    }

    @ShellMethod(key = {"get-all-authors"}, value = "Get all authors from table")
    public String getAllAuthors() {
        return authorService.getAll().toString();
    }

    @ShellMethod(key = {"update-author"}, value = "Update author in table")
    public String updateAuthor(@ShellOption(defaultValue = "0") String id,
                             @ShellOption(defaultValue = "First Name") String firstName,
                             @ShellOption(defaultValue = "Last Name") String lastName) {
        Author foundAuthor = authorService.getById(Long.parseLong(id));
        if (foundAuthor != null) {
            authorService.update(new Author(foundAuthor.getId(), firstName, lastName));
            return String.format("Updated author with id %s", id);
        }
        return "Nothing updated.";
    }

    @ShellMethod(key = {"count-authors"}, value = "Get count of authors in table")
    public String countAuthors() {
        return String.valueOf(authorService.count());
    }

    @ShellMethod(key = {"insert-genre"}, value = "Insert genre into table")
    public String insertGenre(@ShellOption(defaultValue = "Name") String name) {
        long id = genreService.insert(new Genre(name)).getId();
        return String.format("Inserted genre with id %d", id);
    }

    @ShellMethod(key = {"delete-genre"}, value = "Delete genre from table")
    public void deleteGenre(@ShellOption(defaultValue = "0") String id) {
        genreService.deleteById(Long.parseLong(id));
    }

    @ShellMethod(key = {"get-genre"}, value = "Get genre from table by its Id")
    public String getGenre(@ShellOption(defaultValue = "0") String id) {
        return genreService.getById(Long.parseLong(id)).toString();
    }

    @ShellMethod(key = {"get-all-genres"}, value = "Get all genres from table")
    public String getAllGenres() {
        return genreService.getAll().toString();
    }

    @ShellMethod(key = {"update-genre"}, value = "Update genre in table")
    public String updateGenre(@ShellOption(defaultValue = "0") String id,
                             @ShellOption(defaultValue = "Name") String name) {
        Genre foundGenre = genreService.getById(Long.parseLong(id));
        if (foundGenre != null) {
            genreService.update(new Genre(foundGenre.getId(), name));
            return String.format("Updated genre with id %s", id);
        }
        return "Nothing updated.";
    }

    @ShellMethod(key = {"count-genres"}, value = "Get count of genres in table")
    public String countGenres() {
        return String.valueOf(genreService.count());
    }

}
