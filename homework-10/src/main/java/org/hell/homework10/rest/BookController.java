package org.hell.homework10.rest;

import org.hell.homework10.converter.BookEntityToDtoConverter;
import org.hell.homework10.dto.BookDto;
import org.hell.homework10.service.BookService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class BookController {

    private final BookService bookService;
    private final BookEntityToDtoConverter bookEntityToDtoConverter;

    public BookController(BookService bookService, BookEntityToDtoConverter bookEntityToDtoConverter) {
        this.bookService = bookService;
        this.bookEntityToDtoConverter = bookEntityToDtoConverter;
    }

    @GetMapping("/api/books")
    public List<BookDto> getAll() {
        return bookService.findAll().stream()
                .map(bookEntityToDtoConverter::convert)
                .collect(Collectors.toList());
    }

}
