package org.hell.homework06.service;

import org.hell.homework06.repository.AuthorRepositoryJpa;
import org.hell.homework06.model.Author;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AuthorService {

    private final AuthorRepositoryJpa repository;

    public AuthorService(AuthorRepositoryJpa repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public Author findById(long id) {
        return repository.findById(id)
                .orElse(null);
    }

    @Transactional(readOnly = true)
    public Author findByFullName(String firstName, String lastName) {
        return repository.findByFullName(firstName, lastName);
    }

    @Transactional(readOnly = true)
    public List<Author> findAll() {
        return repository.findAll();
    }

    @Transactional
    public void deleteById(long id) {
        repository.deleteById(id);
    }

    @Transactional
    public Author save(Author author) {
        return repository.save(author);
    }

    @Transactional
    public void update(Author author) {
        repository.update(author);
    }

    @Transactional(readOnly = true)
    public long count() {
        return repository.count();
    }

}
