package org.hell.homework05.shell;

import lombok.RequiredArgsConstructor;
import org.hell.homework05.domain.Author;
import org.hell.homework05.domain.Book;
import org.hell.homework05.domain.Genre;
import org.hell.homework05.service.BookService;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
@RequiredArgsConstructor
public class Homework05ApplicationCommands {

    private final BookService bookService;

    @ShellMethod(key = {"insert-book"}, value = "Insert book into table")
    public String insertBook(@ShellOption(defaultValue = "First Name") String authorFirstName,
                             @ShellOption(defaultValue = "Last Name") String authorLastName,
                             @ShellOption(defaultValue = "Untitled") String title,
                             @ShellOption(defaultValue = "Unknown") String genre) {
        long id = bookService.insert(new Book(new Author(authorFirstName, authorLastName), title, new Genre(genre)));
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
    public String getAll() {
        return bookService.getAll().toString();
    }

    @ShellMethod(key = {"update-book"}, value = "Update book in table")
    public String updateBook(@ShellOption(defaultValue = "0") String id,
            @ShellOption(defaultValue = "First Name") String authorFirstName,
                             @ShellOption(defaultValue = "Last Name") String authorLastName,
                             @ShellOption(defaultValue = "Untitled") String title,
                             @ShellOption(defaultValue = "Unknown") String genre) {
        Book foundBook = bookService.getById(Long.parseLong(id));
        if (foundBook != null) {
            bookService.update(new Book(foundBook.getId(), new Author(authorFirstName, authorLastName), title, new Genre(genre)));
            return String.format("Updated book with id %s", id);
        }
        return "Nothing updated.";
    }

}
