package org.hell.homework17.web;

import org.hell.homework17.converter.BookDtoToEntityConverter;
import org.hell.homework17.converter.BookEntityToDtoConverter;
import org.hell.homework17.dto.BookDto;
import org.hell.homework17.model.Book;
import org.hell.homework17.service.AuthorService;
import org.hell.homework17.service.BookService;
import org.hell.homework17.service.GenreService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BookPageController {

    private final BookService bookService;
    private final AuthorService authorService;
    private final GenreService genreService;
    private final BookEntityToDtoConverter bookEntityToDtoConverter;
    private final BookDtoToEntityConverter bookDtoToEntityConverter;

    public BookPageController(BookService bookService, AuthorService authorService, GenreService genreService, BookEntityToDtoConverter bookEntityToDtoConverter, BookDtoToEntityConverter bookDtoToEntityConverter) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.genreService = genreService;
        this.bookEntityToDtoConverter = bookEntityToDtoConverter;
        this.bookDtoToEntityConverter = bookDtoToEntityConverter;
    }

    @GetMapping("/")
    public String listPage() {
        return "list";
    }

    @GetMapping("/edit")
    public String editPage(@RequestParam("id") long id, Model model) {
        Book book = bookService.findById(id);
        if (book == null) {
            throw new NotFoundException();
        }
        model.addAttribute("book", bookEntityToDtoConverter.convert(book));
        model.addAttribute("authors", authorService.findAll());
        model.addAttribute("genres", genreService.findAll());
        return "edit";
    }

    @PostMapping("/edit")
    public String updateBook(BookDto dto, Model model) {
        Book updatedBook = bookService.update(bookDtoToEntityConverter.convert(dto));
        model.addAttribute(updatedBook);
        return "redirect:/";
    }

    @GetMapping("/add")
    public String addBookPage(BookDto book) {
        return "add";
    }

    @PostMapping("/save")
    public String saveBook(BookDto bookDto) {
        bookService.save(bookDtoToEntityConverter.convert(bookDto));
        return "redirect:/";
    }

    @GetMapping("/delete")
    public String deleteBook(@RequestParam("id") long id) {
        bookService.deleteById(id);
        return "redirect:/";
    }
}
