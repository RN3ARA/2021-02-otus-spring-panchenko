package org.hell.homework07.repository;

import org.hell.homework07.model.Comment;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CommentRepository extends CrudRepository<Comment, Long> {

    List<Comment> findAll();

    long countByBookId(long bookId);
}
