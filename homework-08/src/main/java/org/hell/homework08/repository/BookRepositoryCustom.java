package org.hell.homework08.repository;

import org.hell.homework08.model.Author;
import org.hell.homework08.model.Genre;

import java.util.List;

public interface BookRepositoryCustom {

    List<Author> findBookAuthors(String bookId);
    List<Author> findAllAuthors();
    List<Genre> findBookGenres(String bookId);
    List<Genre> findAllGenres();
}
