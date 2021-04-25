package org.hell.homework08.repository;

import org.hell.homework08.model.Author;
import org.hell.homework08.model.Book;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface BookRepository extends MongoRepository<Book, String>, BookRepositoryCustom {

    List<Book> findAll();

    @Query("{'authors.id': ?0}")
    Author findAuthorById(String authorId);
}
