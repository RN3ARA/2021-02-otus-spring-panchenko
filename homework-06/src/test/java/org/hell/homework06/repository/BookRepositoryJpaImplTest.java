package org.hell.homework06.repository;

import org.hell.homework06.model.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
@Import(BookRepositoryJpaImpl.class)
class BookRepositoryJpaImplTest {

    private static final long EXISTING_BOOKS_COUNT = 1L;
    private static final long EXISTING_BOOK_ID = 1L;

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private BookRepositoryJpaImpl repositoryJpa;

    @Test
    void shouldReturnExpectedBooksCount() {
        long actualBooksCount = repositoryJpa.count();
        assertThat(actualBooksCount).isEqualTo(EXISTING_BOOKS_COUNT);
    }

    @Test
    void shouldReturnExpectedBookById() {
        Book expectedBook = entityManager.find(Book.class, EXISTING_BOOK_ID);
        Optional<Book> actualBook = repositoryJpa.findById(EXISTING_BOOK_ID);
        assertThat(actualBook).isPresent().get()
                .usingRecursiveComparison().isEqualTo(expectedBook);
    }

    @Test
    void shouldReturnExpectedBookList() {
        Book expectedBook = entityManager.find(Book.class, EXISTING_BOOK_ID);
        List<Book> actualBookList = repositoryJpa.findAll();
        assertThat(actualBookList)
                .usingFieldByFieldElementComparator()
                .containsExactlyInAnyOrder(expectedBook);
    }

    @Test
    void shouldCorrectDeleteBookById() {
        Book bookForDelete = entityManager.find(Book.class, EXISTING_BOOK_ID);
        assertThat(bookForDelete).isNotNull();
        entityManager.detach(bookForDelete);
        repositoryJpa.deleteById(EXISTING_BOOK_ID);

        Book deletedBook = entityManager.find(Book.class, EXISTING_BOOK_ID);
        assertThat(deletedBook).isNull();
    }

}