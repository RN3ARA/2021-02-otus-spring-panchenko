package org.hell.homework06.repository;

import org.hell.homework06.model.Comment;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
public class CommentRepositoryJpaImpl implements CommentRepositoryJpa {

    @PersistenceContext
    private final EntityManager entityManager;

    public CommentRepositoryJpaImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Comment save(Comment comment) {
        if (comment.getId() != 0) {
            entityManager.persist(comment);
            return comment;
        } else {
            return entityManager.merge(comment);
        }
    }

    @Override
    public Optional<Comment> findById(long id) {
        return Optional.ofNullable(entityManager.find(Comment.class, id));
    }

    @Override
    public List<Comment> findAll() {
        TypedQuery<Comment> query = entityManager.createQuery("select c from Comment c", Comment.class);
        return query.getResultList();
    }

    @Override
    public void deleteById(long id) {
        Comment comment = entityManager.find(Comment.class, id);
        if (comment != null) {
            entityManager.remove(comment);
        }
    }

    @Override
    public long countByBookId(long bookId) {
        TypedQuery<Long> query = entityManager.createQuery(
                "select count(c) from Comment c " +
                "where c.bookId = :bookId", Long.class);
        query.setParameter("bookId", bookId);
        return query.getSingleResult();
    }

    @Override
    public long count() {
        return entityManager.createQuery(
                "select count(c) from Comment c",
                Long.class).getSingleResult();
    }
}
