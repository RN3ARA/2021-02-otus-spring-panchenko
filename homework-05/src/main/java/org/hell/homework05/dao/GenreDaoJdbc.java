package org.hell.homework05.dao;

import org.hell.homework05.domain.Genre;
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
public class GenreDaoJdbc implements GenreDao {

    private final NamedParameterJdbcOperations jdbc;

    public GenreDaoJdbc(NamedParameterJdbcOperations jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public long insert(Genre genre) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("name", genre.getName());
        KeyHolder kh = new GeneratedKeyHolder();
        jdbc.update("insert into genres (name) values (:name)", params, kh, new String[]{"id"});
        return Objects.requireNonNull(kh.getKey()).longValue();
    }

    @Override
    public Genre getById(long id) {
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("id", id);
        return jdbc.queryForObject(
                "select id, name from genres where id = :id", params, new GenreMapper()
        );
    }

    @Override
    public Genre getByName(String name) {
        try {
            MapSqlParameterSource params = new MapSqlParameterSource()
                    .addValue("name", name);
            return jdbc.queryForObject(
                    "select id, name from genres where name = :name", params, new GenreMapper()
            );
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Genre> getAll() {
        return jdbc.query("select id, name from genres", new GenreMapper());
    }

    @Override
    public void update(Genre genre) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", genre.getId());
        params.addValue("name", genre.getName());
        jdbc.update
                ("update genres set name = :name where id = :id", params);
    }

    @Override
    public void deleteById(long id) {
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("id", id);
        jdbc.update(
                "delete from genres where id = :id", params
        );
    }

    @Override
    public int count() {
        //Doubtful about this solution. I guess there's something better but I found nothing except some deprecated stuff
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("id", 0);
        return jdbc.queryForObject("select count(*) from genres where id > :id", params, Integer.class);
    }

    private static class GenreMapper implements RowMapper<Genre> {
        @Override
        public Genre mapRow(ResultSet resultSet, int i) throws SQLException {
            long id = resultSet.getLong("id");
            String name = resultSet.getString("name");
            return new Genre(id, name);
        }
    }
}
