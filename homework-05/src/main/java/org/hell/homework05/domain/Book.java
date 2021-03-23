package org.hell.homework05.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Book {
    private long id;
    private final Author author;
    private final String title;
    private final Genre genre;
}
