package org.hell.homework16.repository;

import org.hell.homework16.model.Comment;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CommentRepository extends CrudRepository<Comment, Long> {

    List<Comment> findAll();

    long countByBookId(long bookId);
}
