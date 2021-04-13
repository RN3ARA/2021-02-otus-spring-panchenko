package org.hell.homework07.service;

import org.hell.homework07.repository.AuthorRepository;
import org.hell.homework07.model.Author;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository repository;

    public AuthorServiceImpl(AuthorRepository repository) {
        this.repository = repository;
    }

    @Override
    public Author findById(long id) {
        return repository.findById(id)
                .orElse(null);
    }

    @Override
    public Author findByFullName(String firstName, String lastName) {
        return repository.findByFirstNameAndLastName(firstName, lastName);
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
