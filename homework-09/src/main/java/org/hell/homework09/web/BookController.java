package org.hell.homework09.web;

import org.hell.homework09.model.Book;
import org.hell.homework09.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/")
    public String listPage(Model model) {
        List<Book> books = bookService.findAll();
        model.addAttribute("books", books);
        return "list";
    }

    @GetMapping("/edit")
    public String editPage(@RequestParam("id") long id, Model model) {
        Book book = bookService.findById(id);
        if (book == null) {
            throw new NotFoundException();
        }
        model.addAttribute("book", book);
        return "edit";
    }

    @PostMapping("/edit")
    public String saveBook(Book book) {
при сохранении Book приходит в контроллер с null-значениями у автора по неизвестным причинам.
        System.out.println(book.getAuthor());
        /*Book updatedBook = bookService.update(book);
        model.addAttribute(updatedBook);*/
        return "redirect:/";
    }
}
