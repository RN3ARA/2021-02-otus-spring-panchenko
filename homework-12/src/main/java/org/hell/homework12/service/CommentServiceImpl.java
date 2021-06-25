package org.hell.homework12.service;

import org.hell.homework12.model.Book;
import org.hell.homework12.model.Comment;
import org.hell.homework12.repository.CommentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository repository;
    private final BookService bookService;

    public CommentServiceImpl(CommentRepository repository, BookService bookService) {
        this.repository = repository;
        this.bookService = bookService;
    }

    @Override
    public Comment findById(long id) {
        return repository.findById(id)
                .orElse(null);
    }

    @Override
    public List<Comment> findAll() {
        return repository.findAll();
    }

    @Override
    public List<Comment> findAllByBookId(long bookId) {
        Book book = bookService.findById(bookId);
        if (book != null) {
            return book.getComments();
        }
        return null;
    }

    @Override
    @Transactional
    public void deleteById(long id) {
        repository.deleteById(id);
    }

    @Override
    @Transactional
    public Comment save(Comment comment) {
        return repository.save(comment);
    }

    @Override
    @Transactional
    public void update(Comment comment) {
        repository.save(comment);
    }

    @Override
    public long count() {
        return repository.count();
    }

    @Override
    public long countByBookId(long bookId) {
        return repository.countByBookId(bookId);
    }
}
