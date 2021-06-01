package org.hell.homework10.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hell.homework10.converter.BookDtoToEntityConverter;
import org.hell.homework10.converter.BookEntityToDtoConverter;
import org.hell.homework10.dto.BookDto;
import org.hell.homework10.model.Author;
import org.hell.homework10.model.Book;
import org.hell.homework10.model.Genre;
import org.hell.homework10.repository.BookRepository;
import org.hell.homework10.service.BookService;
import org.junit.jupiter.api.Test;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.stream.Collectors;

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

    @MockBean
    private BookRepository repository;

    @Autowired
    private ObjectMapper mapper;

    private static final String EXISTING_AUTHOR_FIRST_NAME = "Ilyas";
    private static final String EXISTING_AUTHOR_LAST_NAME = "Esemberlin";
    private static final String EXISTING_GENRE_NAME = "historical";
    private static final String ERROR_MESSAGE = "Could not find";

    @Test
    void shouldReturnCorrectBookById() throws Exception {
        Book book = new Book(1, new Author(1, EXISTING_AUTHOR_FIRST_NAME, EXISTING_AUTHOR_LAST_NAME), "Nomads", new Genre(1, EXISTING_GENRE_NAME));
        when(repository.findById(1)).thenReturn(java.util.Optional.of(book));
        when(service.findById(1)).thenReturn(book);

        BookDto expectedResult = bookEntityToDtoConverter.convert(book);

        mvc.perform(get("/api/books/1"));
                /*.andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(expectedResult)));*/
    }

    /*@Test
    void shouldReturnCorrectBooksList() throws Exception {
        Book book = new Book(1, new Author(1, EXISTING_AUTHOR_FIRST_NAME, EXISTING_AUTHOR_LAST_NAME), "Nomads", new Genre(1, EXISTING_GENRE_NAME));
        List<Book> books = List.of(book);
        given(service.findAll()).willReturn(books);

        List<BookDto> expectedResult = books.stream()
                .map(bookEntityToDtoConverter::convert)
                .collect(Collectors.toList());
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
        book.setId(1);
        given(service.save(any())).willReturn(book);
        String expectedResult = mapper.writeValueAsString(bookEntityToDtoConverter.convert(book));

        mvc.perform(post("/api/books").contentType(APPLICATION_JSON)
                .content(expectedResult))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedResult));
    }

    @Test
    void shouldCorrectDeleteBook() throws Exception {
        mvc.perform(delete("/api/books/1"))
                .andExpect(status().isOk());
        verify(service, times(1)).deleteById(1L);
    }*/
    }