package org.hell.homework07.shell;

import lombok.RequiredArgsConstructor;
import org.hell.homework07.model.Author;
import org.hell.homework07.model.Book;
import org.hell.homework07.model.Comment;
import org.hell.homework07.model.Genre;
import org.hell.homework07.service.*;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
@RequiredArgsConstructor
public class Homework07ApplicationCommands {

    private final BookService bookService;
    private final AuthorService authorService;
    private final GenreService genreService;
    private final CommentService commentService;

    @ShellMethod(key = {"insert-book"}, value = "Insert book into table")
    public String insertBook(@ShellOption(defaultValue = "First Name") String authorFirstName,
                             @ShellOption(defaultValue = "Last Name") String authorLastName,
                             @ShellOption(defaultValue = "Untitled") String title,
                             @ShellOption(defaultValue = "Unknown") String genre) {
        long id = bookService.save(new Book(new Author(authorFirstName, authorLastName), title, new Genre(genre)))
                .getId();
        return String.format("Inserted book with id %d", id);
    }

    @ShellMethod(key = {"delete-book"}, value = "Delete book from table")
    public void deleteBook(@ShellOption(defaultValue = "0") String id) {
        bookService.deleteById(Long.parseLong(id));
    }

    @ShellMethod(key = {"get-book"}, value = "Get book from table by its Id")
    public String getBook(@ShellOption(defaultValue = "0") String id) {
        return bookService.findById(Long.parseLong(id)).toString();
    }

    @ShellMethod(key = {"get-all-books"}, value = "Get all books from table")
    public String getAllBooks() {
        return bookService.findAll().toString();
    }

    @ShellMethod(key = {"update-book"}, value = "Update book in table")
    public String updateBook(@ShellOption(defaultValue = "0") String id,
                             @ShellOption(defaultValue = "Untitled") String title) {
        Book bookForUpdate = bookService.findById(Long.parseLong(id));
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
        long id = authorService.save(new Author(firstName, lastName))
                .getId();
        return String.format("Inserted author with id %d", id);
    }

    @ShellMethod(key = {"delete-author"}, value = "Delete author from table")
    public void deleteAuthor(@ShellOption(defaultValue = "0") String id) {
        authorService.deleteById(Long.parseLong(id));
    }

    @ShellMethod(key = {"get-author"}, value = "Get author from table by its Id")
    public String getAuthor(@ShellOption(defaultValue = "0") String id) {
        return authorService.findById(Long.parseLong(id)).toString();
    }

    @ShellMethod(key = {"get-all-authors"}, value = "Get all authors from table")
    public String getAllAuthors() {
        return authorService.findAll().toString();
    }

    @ShellMethod(key = {"update-author"}, value = "Update author in table")
    public String updateAuthor(@ShellOption(defaultValue = "0") String id,
                             @ShellOption(defaultValue = "First Name") String firstName,
                             @ShellOption(defaultValue = "Last Name") String lastName) {
        Author authorForUpdate = authorService.findById(Long.parseLong(id));
        if (authorForUpdate != null) {
            authorForUpdate.setFirstName(firstName);
            authorForUpdate.setLastName(lastName);
            authorService.update(authorForUpdate);
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
        long id = genreService.save(new Genre(name)).getId();
        return String.format("Inserted genre with id %d", id);
    }

    @ShellMethod(key = {"delete-genre"}, value = "Delete genre from table")
    public void deleteGenre(@ShellOption(defaultValue = "0") String id) {
        genreService.deleteById(Long.parseLong(id));
    }

    @ShellMethod(key = {"get-genre"}, value = "Get genre from table by its Id")
    public String getGenre(@ShellOption(defaultValue = "0") String id) {
        return genreService.findById(Long.parseLong(id)).toString();
    }

    @ShellMethod(key = {"get-all-genres"}, value = "Get all genres from table")
    public String getAllGenres() {
        return genreService.findAll().toString();
    }

    @ShellMethod(key = {"update-genre"}, value = "Update genre in table")
    public String updateGenre(@ShellOption(defaultValue = "0") String id,
                             @ShellOption(defaultValue = "Name") String name) {
        Genre genreForUpdate = genreService.findById(Long.parseLong(id));
        if (genreForUpdate != null) {
            genreForUpdate.setName(name);
            genreService.update(genreForUpdate);
            return String.format("Updated genre with id %s", id);
        }
        return "Nothing updated.";
    }

    @ShellMethod(key = {"count-genres"}, value = "Get count of genres in table")
    public String countGenres() {
        return String.valueOf(genreService.count());
    }

    @ShellMethod(key = {"insert-comment"}, value = "Insert comment into table")
    public String insertComment(@ShellOption(defaultValue = "0") String bookId,
                                @ShellOption(defaultValue = "Something") String reply) {
        Book bookToComment = bookService.findById(Long.parseLong(bookId));
        if (bookToComment != null) {
            long id = commentService.save(new Comment(bookToComment, reply))
                    .getId();
            return String.format("Inserted comment with id %d", id);
        }
        return "Nothing commented";
    }

    @ShellMethod(key = {"delete-comment"}, value = "Delete comment from table")
    public void deleteComment(@ShellOption(defaultValue = "0") String id) {
        commentService.deleteById(Long.parseLong(id));
    }

    @ShellMethod(key = {"get-comment"}, value = "Get comment from table by its Id")
    public String getComment(@ShellOption(defaultValue = "0") String id) {
        return commentService.findById(Long.parseLong(id)).toString();
    }

    @ShellMethod(key = {"get-all-comments"}, value = "Get all comments from table")
    public String getAllComments() {
        return commentService.findAll().toString();
    }

    @ShellMethod(key = {"update-comment"}, value = "Update comment in table")
    public String updateComment(@ShellOption(defaultValue = "0") String id,
                              @ShellOption(defaultValue = "something") String reply) {
        Comment commentForUpdate = commentService.findById(Long.parseLong(id));
        if (commentForUpdate != null) {
            commentForUpdate.setReply(reply);
            commentService.update(commentForUpdate);
            return String.format("Updated comment with id %s", id);
        }
        return "Nothing updated.";
    }

    @ShellMethod(key = {"count-comments"}, value = "Get count of comments in table")
    public String countComments() {
        return String.valueOf(commentService.count());
    }

    @ShellMethod(key = {"get-all-book-comments"}, value = "Get all book comments from table by its id")
    public String getAllComments(@ShellOption (defaultValue = "0") String id) {
        return commentService.findAllByBookId(Long.parseLong(id)).toString();
    }

    @ShellMethod(key = {"count-book-comments"}, value = "Get count of book comments in table by its id")
    public String countComments(@ShellOption(defaultValue = "0") String id) {
        return String.valueOf(commentService.countByBookId(Long.parseLong(id)));
    }

}
