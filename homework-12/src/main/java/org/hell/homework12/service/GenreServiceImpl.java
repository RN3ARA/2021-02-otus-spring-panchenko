package org.hell.homework12.service;

import org.hell.homework12.model.Genre;
import org.hell.homework12.repository.GenreRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GenreServiceImpl implements GenreService {

    private final GenreRepository repository;

    public GenreServiceImpl(GenreRepository repository) {
        this.repository = repository;
    }

    @Override
    public Genre findById(long id) {
        return repository.findById(id)
                .orElse(null);
    }

    @Override
    public Genre findByName(String name) {
        return repository.findByName(name);
    }

    @Override
    public Iterable<Genre> findAll() {
        return repository.findAll();
    }

    @Override
    @Transactional
    public void deleteById(long id) {
        repository.deleteById(id);
    }

    @Override
    @Transactional
    public Genre save(Genre genre) {
        return repository.save(genre);
    }

    @Override
    @Transactional
   public void update(Genre genre) {
        repository.save(genre);
    }

    @Override
    public long count() {
        return repository.count();
    }

}