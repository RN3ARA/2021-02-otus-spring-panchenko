package org.hell.homework10.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hell.homework10.model.Author;
import org.hell.homework10.model.Book;
import org.hell.homework10.model.Genre;

@Data
public class BookDto {
    private long Id;
    private long authorId;
    private String authorFirstName;
    private String authorLastName;
    private String title;
    private long genreId;
    private String genreName;

}
