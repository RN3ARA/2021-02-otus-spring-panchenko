package org.hell.homework08.repository;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.hell.homework08.model.Author;
import org.hell.homework08.model.Book;
import org.hell.homework08.model.Genre;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;

import java.util.List;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

@RequiredArgsConstructor
public class BookRepositoryCustomImpl implements BookRepositoryCustom{

    private final MongoTemplate mongoTemplate;

    @Override
    public List<Author> findBookAuthors(String bookId) {
        val aggregation = newAggregation(
                match(Criteria.where("id").is(bookId))
            , unwind("authors")
                , project().andExclude("id").and("authors.id").as("id")
                        .and("authors.firstName").as("firstName")
                        .and("authors.lastName").as("lastName")
        );
        return mongoTemplate.aggregate(aggregation, Book.class, Author.class)
                .getMappedResults();
    }

    @Override
    public List<Author> findAllAuthors() {
        val aggregation = newAggregation(
                unwind("authors")
                , project().and("authors.id").as("id")
                        .and("authors.firstName").as("firstName")
                        .and("authors.lastName").as("lastName")
        );
        return mongoTemplate.aggregate(aggregation, Book.class, Author.class)
                .getMappedResults();
    }

    @Override
    public List<Genre> findBookGenres(String id) {
        return null;
    }

    @Override
    public List<Genre> findAllGenres() {
        return null;
    }
}
