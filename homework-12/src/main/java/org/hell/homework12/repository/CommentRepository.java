package org.hell.homework12.repository;

import org.hell.homework12.model.Comment;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CommentRepository extends CrudRepository<Comment, Long> {

    List<Comment> findAll();

    long countByBookId(long bookId);
}
