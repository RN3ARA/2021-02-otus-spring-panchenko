package org.hell.homework06.repository;

import org.hell.homework06.model.Genre;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;

@Repository
public class GenreRepositoryJpaImpl implements GenreRepositoryJpa {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Genre insert(Genre genre) {
        if (genre.getId() <= 0) {
            entityManager.persist(genre);
            return genre;
        } else {
            return entityManager.merge(genre);
        }
    }

    @Override
    public Optional<Genre> getById(long id) {
        return Optional.ofNullable(entityManager.find(Genre.class, id));
    }

    @Override
    public Genre getByName(String name) {
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
    public List<Genre> getAll() {
        TypedQuery<Genre> query = entityManager.createQuery("select g from Genre g", Genre.class);
        return query.getResultList();
    }

    @Override
    public void update(Genre genre) {
        Query query = entityManager.createQuery("update Genre g " +
                "set g.name = :name " +
                "where g.id = :id");
        query.setParameter("name", genre.getName());
        query.setParameter("id", genre.getId());
        query.executeUpdate();
    }

    @Override
    public void deleteById(long id) {
        Query query = entityManager.createQuery("delete " +
                "from Genre g " +
                "where g.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    public long count() {
        return entityManager.createQuery(
                "select count(g) from Genre g",
                Long.class).getSingleResult();
    }
}
