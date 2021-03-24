package org.hell.homework05.dao;

import org.hell.homework05.domain.Author;
import org.hell.homework05.domain.Book;
import org.hell.homework05.domain.Genre;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@JdbcTest
@Import({BookDaoJdbc.class,
        AuthorDaoJdbc.class,
        GenreDaoJdbc.class})
class BookDaoJdbcTest {

    private static final int EXPECTED_BOOKS_COUNT = 1;
    private static final int EXISTING_BOOK_ID = 1;
    private static final String EXISTING_AUTHOR_FIRST_NAME = "Ilyas";
    private static final String EXISTING_AUTHOR_LAST_NAME = "Esemberlin";
    private static final String EXISTING_BOOK_TITLE = "Nomads";
    private static final String EXISTING_GENRE_NAME = "historical";

    @Autowired
    private BookDaoJdbc bookDao;

    @Test
    void shouldReturnExpectedBookCount() {
        int actualBooksCount = bookDao.count();
        assertThat(actualBooksCount).isEqualTo(EXPECTED_BOOKS_COUNT);
    }

    @Test
    void shouldInsertBook() {
        Author author = new Author("Guanzhong", "Lo");
        Genre genre = new Genre("historical");
        Book expectedBook = new Book(2, author, "Tripple Reign", genre);
        bookDao.insert(expectedBook);
        Book actualBook = bookDao.getById(expectedBook.getId());
        assertThat(actualBook).usingRecursiveComparison().isEqualTo(expectedBook);
    }

    @Test
    void shouldReturnExpectedBookById() {
        Book expectedBook = new Book(EXISTING_BOOK_ID,
                new Author(EXISTING_AUTHOR_FIRST_NAME, EXISTING_AUTHOR_LAST_NAME),
                EXISTING_BOOK_TITLE, new Genre(EXISTING_GENRE_NAME));
        Book actualBook = bookDao.getById(expectedBook.getId());
        assertThat(actualBook).usingRecursiveComparison().isEqualTo(expectedBook);
    }

    @Test
    void shouldCorrectDeleteBookById() {
        assertThatCode(() -> bookDao.getById(EXISTING_BOOK_ID))
                .doesNotThrowAnyException();

        bookDao.deleteById(EXISTING_BOOK_ID);

        assertThatThrownBy(() -> bookDao.getById(EXISTING_BOOK_ID))
                .isInstanceOf(EmptyResultDataAccessException.class);
    }

    @Test
    void shouldReturnExpectedBookList() {
        Book expectedBook = new Book(EXISTING_BOOK_ID,
                new Author(EXISTING_AUTHOR_FIRST_NAME, EXISTING_AUTHOR_LAST_NAME),
                EXISTING_BOOK_TITLE, new Genre(EXISTING_GENRE_NAME));

        List<Book> actualBooksList = bookDao.getAll();

        assertThat(actualBooksList).usingFieldByFieldElementComparator()
                .containsExactlyInAnyOrder(expectedBook);
    }
}