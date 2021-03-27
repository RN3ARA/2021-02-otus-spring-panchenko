package org.hell.homework05.dao;

import org.hell.homework05.domain.Author;
import org.springframework.dao.EmptyResultDataAccessException;
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
public class AuthorDaoJdbc implements AuthorDao {

    private final NamedParameterJdbcOperations jdbc;

    public AuthorDaoJdbc(NamedParameterJdbcOperations jdbc) {
        this.jdbc = jdbc;
    }

    public long insert(Author author) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("first_name", author.getFirstName());
        params.addValue("last_name", author.getLastName());
        KeyHolder kh = new GeneratedKeyHolder();
        jdbc.update("insert into authors (first_name, last_name) values (:first_name, :last_name)", params, kh, new String[]{"id"});
        return Objects.requireNonNull(kh.getKey()).longValue();
    }

    @Override
    public Author getById(long id) {
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("id", id);
        return jdbc.queryForObject(
                "select id, first_name, last_name from authors where id = :id", params, new AuthorMapper()
        );
    }

    @Override
    public Author getByFullName(String firstName, String lastName) {
        try {
            MapSqlParameterSource params = new MapSqlParameterSource();
            params.addValue("first_name", firstName);
            params.addValue("last_name", lastName);
            return jdbc.queryForObject(
                    "select id, first_name, last_name from authors where first_name = :first_name and last_name = :last_name", params, new AuthorMapper()
            );
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Author> getAll() {
        return jdbc.query("select id, first_name, last_name from authors", new AuthorMapper());
    }

    @Override
    public void update(Author author) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", author.getId());
        params.addValue("first_name", author.getFirstName());
        params.addValue("last_name", author.getLastName());
        jdbc.update
                ("update authors set first_name = :first_name, last_name = :last_name where id = :id", params);
    }

    @Override
    public void deleteById(long id) {
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("id", id);
        jdbc.update(
                "delete from authors where id = :id", params
        );
    }

    @Override
    public int count() {
        //Doubtful about this solution. I guess there's something better but I found nothing except some deprecated stuff
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("id", 0);
        return jdbc.queryForObject("select count(*) from authors where id > :id", params, Integer.class);
    }

    private static class AuthorMapper implements RowMapper<Author> {

        @Override
        public Author mapRow(ResultSet resultSet, int i) throws SQLException {
            long id = resultSet.getLong("id");
            String firstName = resultSet.getString("first_name");
            String lastName = resultSet.getString("last_name");
            return new Author(id, firstName, lastName);
        }
    }
}
