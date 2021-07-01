package org.hell.homework16.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hell.homework16.converter.BookDtoToEntityConverter;
import org.hell.homework16.converter.BookEntityToDtoConverter;
import org.hell.homework16.dto.BookDto;
import org.hell.homework16.model.Author;
import org.hell.homework16.model.Book;
import org.hell.homework16.model.Genre;
import org.hell.homework16.service.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.BDDMockito.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.times;
import static org.mockito.BDDMockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BookController.class)
class BookControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private BookService service;

    @MockBean
    private BookDtoToEntityConverter bookDtoToEntityConverter;

    @MockBean
    private BookEntityToDtoConverter bookEntityToDtoConverter;

    @Autowired
    private ObjectMapper mapper;

    private static final String EXISTING_AUTHOR_FIRST_NAME = "Ilyas";
    private static final String EXISTING_AUTHOR_LAST_NAME = "Esemberlin";
    private static final String EXISTING_GENRE_NAME = "historical";
    private static final String ERROR_MESSAGE = "Could not find";

    @Test
    void shouldReturnCorrectBookById() throws Exception {
        Book book = new Book(1, new Author(1, EXISTING_AUTHOR_FIRST_NAME, EXISTING_AUTHOR_LAST_NAME), "Nomads", new Genre(1, EXISTING_GENRE_NAME));
        when(service.findById(1)).thenReturn(book);

        BookDto expectedResult = createBookDto();
        when(bookEntityToDtoConverter.convert(book)).thenReturn(expectedResult);

        mvc.perform(get("/api/books/1"))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(expectedResult)));
    }

    @Test
    void shouldReturnCorrectBooksList() throws Exception {
        Book book = new Book(1, new Author(1, EXISTING_AUTHOR_FIRST_NAME, EXISTING_AUTHOR_LAST_NAME), "Nomads", new Genre(1, EXISTING_GENRE_NAME));
        List<Book> books = List.of(book);
        when(service.findAll()).thenReturn(books);

        List<BookDto> expectedResult = List.of(createBookDto());
        when(bookEntityToDtoConverter.convert(books.get(0)))
                .thenReturn(expectedResult.get(0));

        mvc.perform(get("/api/books"))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(expectedResult)));
    }

    @Test
    void shouldReturnExpectedErrorWhenBookNotFound() throws Exception {
        given(service.findById(1L)).willReturn(null);

        mvc.perform(get("/api/books/1"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(ERROR_MESSAGE));
    }

    @Test
    void shouldCorrectSaveNewBook() throws Exception {
        Book book = new Book(new Author(1, EXISTING_AUTHOR_FIRST_NAME, EXISTING_AUTHOR_LAST_NAME), "Nomads", new Genre(1, EXISTING_GENRE_NAME));
        when(service.save(any())).thenReturn(book);
        BookDto bookDto = createBookDto();
        when(bookEntityToDtoConverter.convert(book)).thenReturn(bookDto);

        String expectedResult = mapper.writeValueAsString(bookDto);
        mvc.perform(post("/api/books").contentType(APPLICATION_JSON)
                .content(expectedResult))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedResult));
    }

    @Test
    void shouldCorrectDeleteBook() throws Exception {
        Book book = new Book(new Author(1, EXISTING_AUTHOR_FIRST_NAME, EXISTING_AUTHOR_LAST_NAME), "Nomads", new Genre(1, EXISTING_GENRE_NAME));
        when(service.findById(1)).thenReturn(book);
        mvc.perform(delete("/api/books/1"))
                .andExpect(status().isOk());
        verify(service, times(1)).deleteById(1);
    }

    @Test
    void shouldCorrectUpdateBook() throws Exception {
        Book book = new Book(new Author(1, EXISTING_AUTHOR_FIRST_NAME, EXISTING_AUTHOR_LAST_NAME), "Nomads", new Genre(1, EXISTING_GENRE_NAME));
        when(service.findById(1)).thenReturn(book);
        when(service.update(book)).thenAnswer(invocationOnMock -> invocationOnMock.getArgument(0));
        BookDto bookDto = createBookDto();
        when(bookDtoToEntityConverter.convert(bookDto)).thenReturn(book);
        when(bookEntityToDtoConverter.convert(book)).thenReturn(bookDto);

        String expectedResult = mapper.writeValueAsString(bookDto);
        mvc.perform(put("/api/books/1").contentType(APPLICATION_JSON)
                .content(expectedResult))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedResult));
    }

    private BookDto createBookDto() {
        BookDto bookDto = new BookDto();
        bookDto.setId(1);
        bookDto.setAuthorId(1);
        bookDto.setAuthorFirstName(EXISTING_AUTHOR_FIRST_NAME);
        bookDto.setAuthorLastName(EXISTING_AUTHOR_LAST_NAME);
        bookDto.setTitle("Nomads");
        bookDto.setGenreId(1);
        bookDto.setGenreName(EXISTING_GENRE_NAME);
        return bookDto;
    }
    }