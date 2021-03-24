package org.hell.homework05.dao;

import org.hell.homework05.domain.Author;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@JdbcTest
@Import(AuthorDaoJdbc.class)
class AuthorDaoJdbcTest {

    private static final int EXPECTED_AUTHORS_COUNT = 1;
    private static final int EXISTING_AUTHOR_ID = 1;
    private static final String EXISTING_AUTHOR_FIRST_NAME = "Ilyas";
    private static final String EXISTING_AUTHOR_LAST_NAME = "Esemberlin";

    @Autowired
    private AuthorDaoJdbc authorDao;

    @Test
    void shouldReturnExpectedAuthorCount() {
        int actualAuthorsCount = authorDao.count();
        assertThat(actualAuthorsCount).isEqualTo(EXPECTED_AUTHORS_COUNT);
    }

    @Test
    void shouldInsertAuthor() {
        Author expectedAuthor = new Author(2, "Guanzhong", "Lo");
        authorDao.insert(expectedAuthor);
        Author actualAuthor = authorDao.getById(expectedAuthor.getId());
        assertThat(actualAuthor).usingRecursiveComparison().isEqualTo(expectedAuthor);
    }

    @Test
    void shouldReturnExpectedAuthorById() {
        Author expectedAuthor = new Author(EXISTING_AUTHOR_ID, EXISTING_AUTHOR_FIRST_NAME, EXISTING_AUTHOR_LAST_NAME);
        Author actualAuthor = authorDao.getById(expectedAuthor.getId());
        assertThat(actualAuthor).usingRecursiveComparison().isEqualTo(expectedAuthor);
    }

    @Test
    void shouldReturnExpectedAuthorByFirstNameAndLastName() {
        Author expectedAuthor = new Author(EXISTING_AUTHOR_ID, EXISTING_AUTHOR_FIRST_NAME, EXISTING_AUTHOR_LAST_NAME);
        Author actualAuthor = authorDao.getByFullName(expectedAuthor.getFirstName(), expectedAuthor.getLastName());
        assertThat(actualAuthor).usingRecursiveComparison().isEqualTo(expectedAuthor);
    }

    @Test
    void shouldCorrectDeleteAuthorById() {
        assertThatCode(() -> authorDao.getById(EXISTING_AUTHOR_ID))
                .doesNotThrowAnyException();

        authorDao.deleteById(EXISTING_AUTHOR_ID);

        assertThatThrownBy(() -> authorDao.getById(EXISTING_AUTHOR_ID))
                .isInstanceOf(EmptyResultDataAccessException.class);
    }

    @Test
    void shouldReturnExpectedAuthorsList() {
        Author expectedAuthor = new Author(EXISTING_AUTHOR_ID, EXISTING_AUTHOR_FIRST_NAME, EXISTING_AUTHOR_LAST_NAME);
        List<Author> actualPersonList = authorDao.getAll();
        assertThat(actualPersonList)
                .usingFieldByFieldElementComparator()
                .containsExactlyInAnyOrder(expectedAuthor);
    }
}