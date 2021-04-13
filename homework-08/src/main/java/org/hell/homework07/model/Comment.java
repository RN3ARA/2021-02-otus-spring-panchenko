package org.hell.homework07.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Comment {
    private long id;

    private Book book;

    private String reply;

    public Comment(Book book, String reply) {
        this.book = book;
        this.reply = reply;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", book_id=" + book.getId() +
                ", reply='" + reply + '\'' +
                '}';
    }
}
