package org.hell.homework05.service;

import org.hell.homework05.dao.AuthorDao;
import org.hell.homework05.domain.Author;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {

    private final AuthorDao dao;

    public AuthorService(AuthorDao dao) {
        this.dao = dao;
    }

    public Author getById(long id) {
        return dao.getById(id);
    }

    public List<Author> getAll() {
        return dao.getAll();
    }

    public Author getByFullName(String firstName, String lastName) {
        return dao.getByFullName(firstName, lastName);
    }

    public void deleteById(long id) {
        dao.deleteById(id);
    }

    public long insert(Author author) {
        return dao.insert(author);
    }

    public void update(Author author) {
        dao.update(author);
    }

    public int count() {
        return dao.count();
    }

}
