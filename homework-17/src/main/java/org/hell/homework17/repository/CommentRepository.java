package org.hell.homework17.repository;

import org.hell.homework17.model.Comment;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CommentRepository extends CrudRepository<Comment, Long> {

    List<Comment> findAll();

    long countByBookId(long bookId);
}
