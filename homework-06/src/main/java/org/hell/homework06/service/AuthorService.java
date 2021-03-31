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
    public Author getById(long id) {
        return repository.getById(id)
                .orElse(null);
    }

    @Transactional(readOnly = true)
    public Author getByFullName(String firstName, String lastName) {
        return repository.getByFullName(firstName, lastName);
    }

    @Transactional(readOnly = true)
    public List<Author> getAll() {
        return repository.getAll();
    }

    @Transactional
    public void deleteById(long id) {
        repository.deleteById(id);
    }

    @Transactional
    public Author insert(Author author) {
        return repository.insert(author);
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
