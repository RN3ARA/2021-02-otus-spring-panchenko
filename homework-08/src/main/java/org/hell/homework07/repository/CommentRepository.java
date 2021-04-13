package org.hell.homework07.repository;

import org.hell.homework07.model.Comment;

import java.util.List;

public interface CommentRepository {

    List<Comment> findAll();

    long countByBookId(long bookId);
}
