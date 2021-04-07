package org.hell.homework06.service;

import org.hell.homework06.model.Comment;
import org.hell.homework06.repository.CommentRepositoryJpa;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepositoryJpa repository;

    public CommentServiceImpl(CommentRepositoryJpa repository) {
        this.repository = repository;
    }

    @Override
    public Comment findById(long id) {
        return repository.findById(id)
                .orElse(null);
    }

    @Override
    public List<Comment> findAll() {
        return repository.findAll();
    }

    @Override
    public List<Comment> findAllByBookId(long bookId) {
        return repository.findAllByBookId(bookId);
    }

    @Override
    @Transactional
    public void deleteById(long id) {
        repository.deleteById(id);
    }

    @Override
    @Transactional
    public Comment save(Comment comment) {
        return repository.save(comment);
    }

    @Override
    @Transactional
    public void update(Comment comment) {
        repository.save(comment);
    }

    @Override
    public long count() {
        return repository.count();
    }

    @Override
    public long countByBookId(long bookId) {
        return repository.countByBookId(bookId);
    }
}
