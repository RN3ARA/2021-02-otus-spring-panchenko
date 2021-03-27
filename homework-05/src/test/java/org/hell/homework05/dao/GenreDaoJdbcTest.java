package org.hell.homework05.dao;

import org.hell.homework05.domain.Genre;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@JdbcTest
@Import(GenreDaoJdbc.class)
class GenreDaoJdbcTest {

    private static final int EXPECTED_GENRES_COUNT = 1;
    private static final int EXISTING_GENRE_ID = 1;
    private static final String EXISTING_GENRE_NAME = "historical";

    @Autowired
    private GenreDaoJdbc genreDao;

    @Test
    void shouldReturnExpectedGenreCount() {
        int actualGenresCount = genreDao.count();
        assertThat(actualGenresCount).isEqualTo(EXPECTED_GENRES_COUNT);
    }

    @Test
    void shouldInsertGenre() {
        Genre expectedGenre = new Genre(2, "unknown");
        genreDao.insert(expectedGenre);
        Genre actualGenre = genreDao.getById(expectedGenre.getId());
        assertThat(actualGenre).usingRecursiveComparison().isEqualTo(expectedGenre);
    }

    @Test
    void shouldReturnExpectedGenreById() {
        Genre expectedGenre = new Genre(EXISTING_GENRE_ID, EXISTING_GENRE_NAME);
        Genre actualGenre = genreDao.getById(expectedGenre.getId());
        assertThat(actualGenre).usingRecursiveComparison().isEqualTo(expectedGenre);
    }

    @Test
    void shouldReturnExpectedGenreByName() {
        Genre expectedGenre = new Genre(EXISTING_GENRE_ID, EXISTING_GENRE_NAME);
        Genre actualGenre = genreDao.getByName(expectedGenre.getName());
        assertThat(actualGenre).usingRecursiveComparison().isEqualTo(expectedGenre);
    }

    @Test
    void shouldCorrectDeleteGenreById() {
        assertThatCode(() -> genreDao.getById(EXISTING_GENRE_ID))
                .doesNotThrowAnyException();

        genreDao.deleteById(EXISTING_GENRE_ID);

        assertThatThrownBy(() -> genreDao.getById(EXISTING_GENRE_ID))
                .isInstanceOf(EmptyResultDataAccessException.class);
    }

    @Test
    void shouldReturnExpectedGenresList() {
        Genre expectedGenre = new Genre(EXISTING_GENRE_ID, EXISTING_GENRE_NAME);
        List<Genre> actualGenreList = genreDao.getAll();
        assertThat(actualGenreList)
                .usingFieldByFieldElementComparator()
                .containsExactlyInAnyOrder(expectedGenre);
    }
}