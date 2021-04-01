package org.hell.homework06.repository;

import org.hell.homework06.model.Genre;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
@Import(GenreRepositoryJpaImpl.class)
class GenreRepositoryJpaImplTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private GenreRepositoryJpaImpl repositoryJpa;

    private static final long EXISTING_GENRES_COUNT = 1L;
    private static final long EXISTING_GENRE_ID = 1L;
    private static final String EXISTING_GENRE_NAME = "historical";

    @Test
    void shouldReturnExpectedGenreCount() {
        long actualGenresCount = repositoryJpa.count();
        assertThat(actualGenresCount).isEqualTo(EXISTING_GENRES_COUNT);
    }

    @Test
    void shouldInsertGenre() {
        Genre expectedGenre = new Genre(2, "unknown");
        repositoryJpa.insert(expectedGenre);
        Genre actualGenre = entityManager.find(Genre.class, expectedGenre.getId());
        assertThat(actualGenre).usingRecursiveComparison().isEqualTo(expectedGenre);
    }

    @Test
    void shouldReturnExpectedGenreById() {
        Genre expectedGenre = entityManager.find(Genre.class, EXISTING_GENRE_ID);
        Optional<Genre> actualGenre = repositoryJpa.getById(EXISTING_GENRE_ID);
        assertThat(actualGenre).isPresent().get()
                .usingRecursiveComparison().isEqualTo(expectedGenre);
    }

    @Test
    void shouldReturnExpectedGenreByName() {
        Genre expectedGenre = new Genre(EXISTING_GENRE_ID, EXISTING_GENRE_NAME);
        Genre actualGenre = repositoryJpa.getByName(expectedGenre.getName());
        assertThat(actualGenre).usingRecursiveComparison().isEqualTo(expectedGenre);
    }

    @Test
    void shouldReturnExpectedGenreList() {
        Genre expectedGenre = new Genre(EXISTING_GENRE_ID, EXISTING_GENRE_NAME);
        List<Genre> actualGenreList = repositoryJpa.getAll();
        assertThat(actualGenreList)
                .usingFieldByFieldElementComparator()
                .containsExactlyInAnyOrder(expectedGenre);
    }

    @Test
    void shouldCorrectDeleteGenreById() {
        Genre genreForDelete = entityManager.find(Genre.class, EXISTING_GENRE_ID);
        assertThat(genreForDelete).isNotNull();
        entityManager.detach(genreForDelete);
        repositoryJpa.deleteById(EXISTING_GENRE_ID);

        Genre deletedGenre = entityManager.find(Genre.class, EXISTING_GENRE_ID);
        assertThat(deletedGenre).isNull();
    }

    }