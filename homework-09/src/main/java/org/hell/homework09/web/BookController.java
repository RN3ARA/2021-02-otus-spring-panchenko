package org.hell.homework09.web;

import org.hell.homework09.dto.BookDto;
import org.hell.homework09.model.Book;
import org.hell.homework09.service.AuthorService;
import org.hell.homework09.service.BookService;
import org.hell.homework09.service.GenreService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class BookController {

    private final BookService bookService;
    private final AuthorService authorService;
    private final GenreService genreService;

    public BookController(BookService bookService, AuthorService authorService, GenreService genreService) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.genreService = genreService;
    }

    @GetMapping("/")
    public String listPage(Model model) {
        List<BookDto> books = bookService.findAll().stream()
                .map(BookDto::toDto)
                .collect(Collectors.toList());
        model.addAttribute("books", books);
        return "list";
    }

    @GetMapping("/edit")
    public String editPage(@RequestParam("id") long id, Model model) {
        Book book = bookService.findById(id);
        if (book == null) {
            throw new NotFoundException();
        }
        model.addAttribute("book", BookDto.toDto(book));
        model.addAttribute("authors", authorService.findAll());
        model.addAttribute("genres", genreService.findAll());
        return "edit";
    }

@PostMapping("/edit")
    public String updateBook(BookDto book, Model model) {
        Book updatedBook = bookService.update(BookDto.toDomainObject(book));
        model.addAttribute(updatedBook);
        return "redirect:/";
    }

    @GetMapping("/add")
    public String addBookPage(BookDto book) {
        return "add";
    }

    @PostMapping("/save")
    public String saveBook(BookDto bookDto) {
        bookService.save(BookDto.toDomainObject(bookDto));
        return "redirect:/";
    }

    @GetMapping("/delete")
    public String deleteBook(@RequestParam("id") long id) {
        bookService.deleteById(id);
        return "redirect:/";
    }
}
