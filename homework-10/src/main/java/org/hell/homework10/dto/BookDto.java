package org.hell.homework10.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hell.homework10.model.Author;
import org.hell.homework10.model.Book;
import org.hell.homework10.model.Genre;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDto {
    private long Id;
    private long authorId;
    private String authorFirstName;
    private String authorLastName;
    private String title;
    private long genreId;
    private String genreName;

    public static BookDto toDto(Book book) {
        return new BookDto(book.getId(), book.getAuthor().getId(), book.getAuthor().getFirstName(), book.getAuthor().getLastName(),
                book.getTitle(), book.getGenre().getId(), book.getGenre().getName());
    }

    public static Book toDomainObject(BookDto dto) {
        Book book = new Book();
        book.setId(dto.getId());
        book.setAuthor(new Author(dto.getAuthorId(), dto.getAuthorFirstName(), dto.getAuthorLastName()));
        book.setTitle(dto.getTitle());
        book.setGenre(new Genre(dto.getGenreId(), dto.genreName));
        return book;
    }
}
