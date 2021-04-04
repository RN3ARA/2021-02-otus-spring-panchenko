package org.hell.homework06.service;

import org.hell.homework06.model.Comment;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CommentService {

    Comment findById(long id);

    List<Comment> findAll();

    List<Comment> findAllByBookId(long bookId);

    void deleteById(long id);

    Comment save(Comment comment);

    void update(Comment comment);

    long count();

    long countByBookId(long bookId);
}
