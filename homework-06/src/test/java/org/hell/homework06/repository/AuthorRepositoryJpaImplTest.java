package org.hell.homework06.repository;

import org.hell.homework06.model.Author;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
@Import(AuthorRepositoryJpaImpl.class)
class AuthorRepositoryJpaImplTest {

    private static final long EXISTING_AUTHORS_COUNT = 1L;
    private static final long EXISTING_AUTHOR_ID = 1L;
    private static final String EXISTING_AUTHOR_FIRST_NAME = "Ilyas";
    private static final String EXISTING_AUTHOR_LAST_NAME = "Esemberlin";

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private AuthorRepositoryJpaImpl repositoryJpa;

    @Test
    void shouldReturnExpectedAuthorCount() {
        long actualAuthorsCount = repositoryJpa.count();
        assertThat(actualAuthorsCount).isEqualTo(EXISTING_AUTHORS_COUNT);
    }

    @Test
    void shouldInsertAuthor() {
        Author expectedAuthor = new Author(2L, "Guanzhong", "Lo");
        repositoryJpa.save(expectedAuthor);
        Optional<Author> actualAuthor = repositoryJpa.findById(expectedAuthor.getId());
        assertThat(actualAuthor).isPresent().get()
                .usingRecursiveComparison().isEqualTo(expectedAuthor);
    }

    @Test
    void shouldReturnExpectedAuthorById() {
        Author expectedAuthor = entityManager.find(Author.class, EXISTING_AUTHOR_ID);
        Optional<Author> actualAuthor = repositoryJpa.findById(EXISTING_AUTHOR_ID);
        assertThat(actualAuthor).isPresent().get()
                .usingRecursiveComparison().isEqualTo(expectedAuthor);
    }

    @Test
    void shouldReturnExpectedAuthorByFirstNameAndLastName() {
        Author expectedAuthor = new Author(EXISTING_AUTHOR_ID, EXISTING_AUTHOR_FIRST_NAME, EXISTING_AUTHOR_LAST_NAME);
        Author actualAuthor = repositoryJpa.findByFullName(expectedAuthor.getFirstName(), expectedAuthor.getLastName());
        assertThat(actualAuthor).usingRecursiveComparison().isEqualTo(expectedAuthor);
    }

    @Test
    void shouldReturnExpectedAuthorsList() {
        Author expectedAuthor = new Author(EXISTING_AUTHOR_ID, EXISTING_AUTHOR_FIRST_NAME, EXISTING_AUTHOR_LAST_NAME);
        List<Author> actualAuthorList = repositoryJpa.findAll();
        assertThat(actualAuthorList)
                .usingFieldByFieldElementComparator()
                .containsExactlyInAnyOrder(expectedAuthor);
    }

    @Test
    void shouldCorrectDeleteAuthorById() {
        Author authorForDelete = entityManager.find(Author.class, EXISTING_AUTHOR_ID);
        assertThat(authorForDelete).isNotNull();
        entityManager.detach(authorForDelete);
        repositoryJpa.deleteById(EXISTING_AUTHOR_ID);

        Author deletedAuthor = entityManager.find(Author.class, EXISTING_AUTHOR_ID);
        assertThat(deletedAuthor).isNull();
    }
}