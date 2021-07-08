package org.hell.homework16.model;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private Book book;

    @Column(name = "reply")
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
