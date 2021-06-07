package org.hell.homework06.repository;

import org.hell.homework06.model.Book;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class BookRepositoryJpaImpl implements BookRepositoryJpa {

    @PersistenceContext
    private final EntityManager entityManager;
    private final EntityGraph<?> entityGraph;

    public BookRepositoryJpaImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.entityGraph = entityManager.getEntityGraph("book-entity-graph");
    }

    @Override
    public Book save(Book book) {
        if (book.getId() <= 0) {
            entityManager.persist(book);
            return book;
        } else {
            return entityManager.merge(book);
        }
    }

    @Override
    public Optional<Book> findById(long id) {
        Map<String, Object> hints = new HashMap<>();
        hints.put("javax.persistence.fetchgraph", this.entityGraph);
        return Optional.ofNullable(entityManager.find(Book.class, id, hints));
    }

    @Override
    public List<Book> findAll() {
        TypedQuery<Book> query = entityManager.createQuery("select b from Book b", Book.class)
                .setHint("javax.persistence.fetchgraph", this.entityGraph);
        return query.getResultList();
    }

    @Override
    public void deleteById(long id) {
        Book book = entityManager.find(Book.class, id);
        if (book != null) {
            entityManager.remove(book);
        }
    }

    @Override
    public long count() {
        return entityManager.createQuery(
                "select count(b) from Book b",
                Long.class).getSingleResult();
    }
}
