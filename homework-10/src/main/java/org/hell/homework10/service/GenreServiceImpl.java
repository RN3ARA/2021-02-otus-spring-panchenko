package org.hell.homework10.service;

import org.hell.homework10.repository.GenreRepository;
import org.hell.homework10.model.Genre;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GenreServiceImpl implements GenreService {

    private final GenreRepository repository;

    public GenreServiceImpl(GenreRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public Genre findById(long id) {
        return repository.findById(id)
                .orElse(null);
    }

    @Override
    @Transactional
    public Genre findByName(String name) {
        return repository.findByName(name);
    }

    @Override
    @Transactional
    public List<Genre> findAll() {
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
    @Transactional
    public long count() {
        return repository.count();
    }

}