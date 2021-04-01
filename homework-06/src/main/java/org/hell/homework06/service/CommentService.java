package org.hell.homework06.service;

import org.hell.homework06.model.Comment;
import org.hell.homework06.repository.CommentRepositoryJpa;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CommentService {

    private final CommentRepositoryJpa repository;

    public CommentService(CommentRepositoryJpa repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public Comment findById(long id) {
        return repository.findById(id)
                .orElse(null);
    }

    @Transactional(readOnly = true)
    public List<Comment> findAll() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Comment> findAllByBookId(long bookId) {
        return repository.findAllByBookId(bookId);
    }

    @Transactional
    public void deleteById(long id) {
        repository.deleteById(id);
    }

    @Transactional
    public Comment save(Comment comment) {
        return repository.save(comment);
    }

    @Transactional
    public void update(Comment comment) {
        repository.update(comment);
    }

    @Transactional(readOnly = true)
    public long count() {
        return repository.count();
    }

    @Transactional(readOnly = true)
    public long countByBookId(long bookId) {
        return repository.countByBookId(bookId);
    }
}
