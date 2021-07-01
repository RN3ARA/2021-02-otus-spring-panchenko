package org.hell.homework12.rest;

import lombok.AllArgsConstructor;
import org.hell.homework12.converter.BookDtoToEntityConverter;
import org.hell.homework12.converter.BookEntityToDtoConverter;
import org.hell.homework12.dto.BookDto;
import org.hell.homework12.model.Book;
import org.hell.homework12.service.BookService;
import org.hell.homework12.web.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
public class BookController {

    private final BookService bookService;
    private final BookEntityToDtoConverter bookEntityToDtoConverter;
    private final BookDtoToEntityConverter bookDtoToEntityConverter;

    @GetMapping("/api/books")
    public List<BookDto> getAllBooks() {
        return bookService.findAll().stream()
                .map(bookEntityToDtoConverter::convert)
                .collect(Collectors.toList());
    }

    @GetMapping("/api/books/{id}")
    public BookDto getBookById(@PathVariable("id") long id) {
        Book book = bookService.findById(id);
        if (book == null) {
            throw new NotFoundException();
        }
        return bookEntityToDtoConverter.convert(book);
    }

    @PostMapping("/api/books")
    public BookDto createNewBook(@RequestBody BookDto dto) {
        Book book = bookDtoToEntityConverter.convert(dto);
        Book savedBook = bookService.save(book);
        return bookEntityToDtoConverter.convert(savedBook);
    }

    @DeleteMapping("/api/books/{id}")
    public void deleteBookById(@PathVariable("id") long id) {
        Book book = bookService.findById(id);
        if (book == null) {
            throw new NotFoundException();
        }
        bookService.deleteById(id);
    }

    @PutMapping("/api/books/{id}")
    public BookDto updateBookById(@PathVariable("id") long id, @RequestBody BookDto dto) {
        Book book = bookService.findById(id);
        if (book == null) {
            throw new NotFoundException();
        }
        Book updatedBook = bookDtoToEntityConverter.convert(dto);
        return bookEntityToDtoConverter.convert(bookService.update(updatedBook));
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<String> handleNotFound() {
        return ResponseEntity.badRequest().body("Could not find");
    }
}
