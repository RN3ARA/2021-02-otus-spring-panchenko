package org.hell.homework10.rest;

import org.hell.homework10.dto.BookDto;
import org.hell.homework10.service.BookService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/api/books")
    public List<BookDto> getAll() {
        return bookService.findAll().stream()
                .map(BookDto::toDto)
                .collect(Collectors.toList());
    }

}
