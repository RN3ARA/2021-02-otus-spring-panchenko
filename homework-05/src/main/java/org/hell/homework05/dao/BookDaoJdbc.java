package org.hell.homework05.dao;

import org.hell.homework05.domain.Author;
import org.hell.homework05.domain.Book;
import org.hell.homework05.domain.Genre;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

@Repository
public class BookDaoJdbc implements BookDao {

    private final NamedParameterJdbcOperations jdbc;
    private final AuthorDao authorDao;
    private final GenreDao genreDao;

    public BookDaoJdbc(NamedParameterJdbcOperations jdbc, AuthorDao authorDao, GenreDao genreDao) {
        this.jdbc = jdbc;
        this.authorDao = authorDao;
        this.genreDao = genreDao;
    }

    @Override
    public long insert(Book book) {
        long authorId = getAuthorId(book);
        long genreId = getGenreId(book);
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("author_id", authorId);
        params.addValue("title", book.getTitle());
        params.addValue("genre_id", genreId);
        KeyHolder kh = new GeneratedKeyHolder();
        jdbc.update("insert into books (author_id, title, genre_id) values (:author_id, :title, :genre_id)", params, kh, new String[]{"id"});
        return Objects.requireNonNull(kh.getKey()).longValue();
    }

    @Override
    public Book getById(long id) {
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("id", id);
        return jdbc.queryForObject(
                "select b.id, a.first_name, a.last_name, b.title, g.name "
                        + "from books as b "
                        + "join authors as a on a.id = b.author_id "
                        + "join genres as g on g.id = b.genre_id "
                        + "where b.id = :id", params, new BookMapper()
        );
    }

    @Override
    public List<Book> getAll() {
        return jdbc.query(
                "select b.id, a.first_name, a.last_name, b.title, g.name "
                        + "from books as b "
                        + "join authors as a on a.id = b.author_id "
                        + "join genres as g on g.id = b.genre_id", new BookMapper()
        );
    }

    @Override
    public void update(Book book) {
        long authorId = getAuthorId(book);
        long genreId = getGenreId(book);
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", book.getId());
        params.addValue("author_id", authorId);
        params.addValue("title", book.getTitle());
        params.addValue("genre_id", genreId);
        jdbc.update("update books set author_id = :author_id, title = :title, genre_id = :genre_id where id = :id", params);
    }

    @Override
    public void deleteById(long id) {
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("id", id);
        jdbc.update(
                "delete from books where id = :id", params
        );
    }

    @Override
    public int count() {
        //Doubtful about this solution. I guess there's something better but I found nothing except some deprecated stuff
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("id", 0);
        return jdbc.queryForObject("select count(*) from books where id > :id", params, Integer.class);
    }

    private long getAuthorId(Book book) {
        Author author = authorDao.getByFullName(book.getAuthor().getFirstName(),
                book.getAuthor().getLastName());
        if (author == null) {
            return authorDao.insert(new Author(book.getAuthor().getFirstName(), book.getAuthor().getLastName()));
        } else {
            return author.getId();
        }
    }

    private long getGenreId(Book book) {
        Genre genre = genreDao.getByName(book.getGenre().getName());
        if (genre == null) {
            return genreDao.insert(new Genre(book.getGenre().getName()));
        } else {
            return genre.getId();
        }
    }

    private static class BookMapper implements RowMapper<Book> {
        @Override
        public Book mapRow(ResultSet resultSet, int i) throws SQLException {
            long id = resultSet.getLong("id");
            String authorFirstName = resultSet.getString("first_name");
            String authorLastName = resultSet.getString("last_name");
            String title = resultSet.getString("title");
            String genreName = resultSet.getString("name");
            return new Book(id, new Author(authorFirstName, authorLastName), title, new Genre(genreName));
        }
    }
}
