package org.hell.homework06.repository;

import org.hell.homework06.model.Author;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;

@Repository
public class AuthorRepositoryJpaImpl implements AuthorRepositoryJpa {

    @PersistenceContext
    private final EntityManager entityManager;

    public AuthorRepositoryJpaImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Author save(Author author) {
        if (author.getId() <= 0) {
            entityManager.persist(author);
            return author;
        } else {
            return entityManager.merge(author);
        }
    }

    @Override
    public Optional<Author> findById(long id) {
        return Optional.ofNullable(entityManager.find(Author.class, id));
    }

    @Override
    public Author findByFullName(String firstName, String lastName) {
        TypedQuery<Author> query = entityManager.createQuery(
                "select a from Author a " +
                        "where a.firstName = :firstName and a.lastName = :lastName", Author.class);
        query.setParameter("firstName", firstName);
        query.setParameter("lastName", lastName);
        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public List<Author> findAll() {
        TypedQuery<Author> query = entityManager.createQuery("select a from Author a", Author.class);
        return query.getResultList();
    }

    @Override
    public void update(Author author) {
        Query query = entityManager.createQuery("update Author a " +
                "set a.firstName = :firstName, " +
                "a.lastName = :lastName " +
                "where a.id = :id");
        query.setParameter("firstName", author.getFirstName());
        query.setParameter("lastName", author.getLastName());
        query.setParameter("id", author.getId());
        query.executeUpdate();
    }

    @Override
    public void deleteById(long id) {
        Query query = entityManager.createQuery("delete " +
                "from Author a " +
        "where a.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    public long count() {
        return entityManager.createQuery(
                "select count(a) from Author a",
                Long.class).getSingleResult();
    }
}
