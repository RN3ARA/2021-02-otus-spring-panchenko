package org.hell.homework17.dto;

import lombok.Data;

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
