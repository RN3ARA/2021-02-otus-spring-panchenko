package org.hell.homework06.service;

import org.hell.homework06.repository.AuthorRepositoryJpa;
import org.hell.homework06.model.Author;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepositoryJpa repository;

    public AuthorServiceImpl(AuthorRepositoryJpa repository) {
        this.repository = repository;
    }

    @Override
    public Author findById(long id) {
        return repository.findById(id)
                .orElse(null);
    }

    @Override
    public Author findByFullName(String firstName, String lastName) {
        return repository.findByFullName(firstName, lastName);
    }

    @Override
    public List<Author> findAll() {
        return repository.findAll();
    }

    @Override
    @Transactional
    public void deleteById(long id) {
        repository.deleteById(id);
    }

    @Override
    @Transactional
    public Author save(Author author) {
        return repository.save(author);
    }

    @Override
    @Transactional
    public void update(Author author) {
        repository.save(author);
    }

    @Override
    public long count() {
        return repository.count();
    }

}
