package org.hell.homework06.service;

import org.hell.homework06.dao.GenreDao;
import org.hell.homework06.domain.Genre;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreService {

    private final GenreDao dao;

    public GenreService(GenreDao dao) {
        this.dao = dao;
    }

    public Genre getById(long id) {
        return dao.getById(id);
    }

    public List<Genre> getAll() {
        return dao.getAll();
    }

    public Genre getByName(String name) {
        return dao.getByName(name);
    }

    public void deleteById(long id) {
        dao.deleteById(id);
    }

    public long insert(Genre genre) {
        return dao.insert(genre);
    }

    public void update(Genre genre) {
        dao.update(genre);
    }

    public int count() {
        return dao.count();
    }

}