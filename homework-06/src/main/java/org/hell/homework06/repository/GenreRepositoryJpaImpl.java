package org.hell.homework06.repository;

import org.hell.homework06.model.Genre;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;

@Repository
public class GenreRepositoryJpaImpl implements GenreRepositoryJpa {

    @PersistenceContext
    private final EntityManager entityManager;

    public GenreRepositoryJpaImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Genre save(Genre genre) {
        if (genre.getId() <= 0) {
            entityManager.persist(genre);
            return genre;
        } else {
            return entityManager.merge(genre);
        }
    }

    @Override
    public Optional<Genre> findById(long id) {
        return Optional.ofNullable(entityManager.find(Genre.class, id));
    }

    @Override
    public Genre findByName(String name) {
        TypedQuery<Genre> query = entityManager.createQuery(
                "select g from Genre g " +
                        "where g.name = :name", Genre.class);
        query.setParameter("name", name);
        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public List<Genre> findAll() {
        TypedQuery<Genre> query = entityManager.createQuery("select g from Genre g", Genre.class);
        return query.getResultList();
    }

    @Override
    public void deleteById(long id) {
        Genre genre = entityManager.find(Genre.class, id);
        if (genre != null) {
            entityManager.remove(genre);
            entityManager.flush();
            entityManager.clear();
        }
    }

    @Override
    public long count() {
        return entityManager.createQuery(
                "select count(g) from Genre g",
                Long.class).getSingleResult();
    }
}
