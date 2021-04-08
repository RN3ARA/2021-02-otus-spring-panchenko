package org.hell.homework06.repository;

import org.hell.homework06.model.Book;
import org.hell.homework06.model.Comment;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import(CommentRepositoryJpaImpl.class)
class CommentRepositoryJpaImplTest {

    private static final long EXISTING_COMMENTS_COUNT = 3L;
    private static final long EXISTING_COMMENTS_COUNT_OF_BOOK = 2L;
    private static final long EXISTING_COMMENT_ID = 1L;
    private static final String EXISTING_COMMENT_TEXT = "Amazing.";
    private static final long EXISTING_BOOK_ID = 1L;

    @Autowired
    private CommentRepositoryJpaImpl repositoryJpa;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    void shouldReturnExpectedCommentsCount() {
        long actualCommentsCount = repositoryJpa.count();
        assertThat(actualCommentsCount).isEqualTo(EXISTING_COMMENTS_COUNT);
    }

    @Test
    void shouldReturnExpectedCommentsCountOfBook() {
        long actualCommentsCount = repositoryJpa.countByBookId(EXISTING_BOOK_ID);
        assertThat(actualCommentsCount).isEqualTo(EXISTING_COMMENTS_COUNT_OF_BOOK);
    }

    @Test
    void shouldReturnExpectedCommentById() {
        Comment expectedComment = entityManager.find(Comment.class, EXISTING_COMMENT_ID);
        Optional<Comment> actualComment = repositoryJpa.findById(EXISTING_COMMENT_ID);
        assertThat(actualComment).isPresent().get()
                .usingRecursiveComparison().isEqualTo(expectedComment);
    }

    @Test
    void shouldReturnExpectedCommentList() {
        Book book = entityManager.find(Book.class, EXISTING_BOOK_ID);
        Comment expectedComment = new Comment(EXISTING_COMMENT_ID, book, EXISTING_COMMENT_TEXT);
        List<Comment> actualCommentList = repositoryJpa.findAll();
        assertThat(actualCommentList)
                .usingFieldByFieldElementComparator()
                .contains(expectedComment);
    }

    @Test
    void shouldCorrectDeleteCommentById() {
        Comment commentForDelete = entityManager.find(Comment.class, EXISTING_COMMENT_ID);
        assertThat(commentForDelete).isNotNull();
        entityManager.detach(commentForDelete);
        repositoryJpa.deleteById(EXISTING_COMMENT_ID);

        Comment deletedComment = entityManager.find(Comment.class, EXISTING_COMMENT_ID);
        assertThat(deletedComment).isNull();
    }

}