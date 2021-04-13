package org.hell.homework07.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    private long id;

    private Author author;

private String title;

    private Genre genre;

    private List<Comment> comments;

    public Book(Author author, String title, Genre genre) {
        this.author = author;
        this.title = title;
        this.genre = genre;
    }
}
