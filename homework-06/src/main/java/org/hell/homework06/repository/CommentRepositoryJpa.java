package org.hell.homework06.repository;

import org.hell.homework06.model.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentRepositoryJpa {

    Comment save(Comment comment);

    Optional<Comment> findById(long id);

    List<Comment> findAll();
    List<Comment> findAllByBookId(long bookId);

    void update(Comment comment);

    void deleteById(long id);

    long countByBookId(long bookId);
    long count();
}
