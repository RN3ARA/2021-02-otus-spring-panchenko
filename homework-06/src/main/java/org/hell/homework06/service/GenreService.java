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
    public Genre findById(long id) {
        return repository.findById(id)
                .orElse(null);
    }

    @Transactional(readOnly = true)
    public Genre findByName(String name) {
        return repository.findByName(name);
    }

    @Transactional(readOnly = true)
    public List<Genre> findAll() {
        return repository.findAll();
    }

    @Transactional
    public void deleteById(long id) {
        repository.deleteById(id);
    }

    @Transactional
    public Genre save(Genre genre) {
        return repository.save(genre);
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