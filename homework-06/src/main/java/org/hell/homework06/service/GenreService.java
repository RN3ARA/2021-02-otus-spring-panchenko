package org.hell.homework06.service;

import org.hell.homework06.repository.GenreRepositoryJpa;
import org.hell.homework06.model.Genre;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GenreService {

    private final GenreRepositoryJpa repository;

    public GenreService(GenreRepositoryJpa repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public Genre getById(long id) {
        return repository.getById(id)
                .orElse(null);
    }

    @Transactional(readOnly = true)
    public Genre getByName(String name) {
        return repository.getByName(name);
    }
    @Transactional(readOnly = true)
    public List<Genre> getAll() {
        return repository.getAll();
    }

    @Transactional
    public void deleteById(long id) {
        repository.deleteById(id);
    }

    @Transactional
    public Genre insert(Genre genre) {
        return repository.insert(genre);
    }

    @Transactional
    public void update(Genre genre) {
        repository.update(genre);
    }

    @Transactional(readOnly = true)
    public long count() {
        return repository.count();
    }

}