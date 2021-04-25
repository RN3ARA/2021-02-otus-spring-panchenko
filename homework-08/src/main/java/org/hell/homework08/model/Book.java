package org.hell.homework08.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "books")
public class Book {

    @Id
    private String id;

    private List<Author> authors = new ArrayList<>();

private String title;

    private List<Genre> genres = new ArrayList<>();

    private List<Comment> comments = new ArrayList<>();

    public Book(String authorFirstName, String authorLastName, String title, String genre) {
        this.authors.add(new Author(authorFirstName, authorLastName));
        this.title = title;
        this.genres.add(new Genre(genre));
    }
}
