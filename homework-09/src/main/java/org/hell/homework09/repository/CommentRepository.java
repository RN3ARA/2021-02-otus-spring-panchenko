package org.hell.homework09.repository;

import org.hell.homework09.model.Comment;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CommentRepository extends CrudRepository<Comment, Long> {

    List<Comment> findAll();

    long countByBookId(long bookId);
}
