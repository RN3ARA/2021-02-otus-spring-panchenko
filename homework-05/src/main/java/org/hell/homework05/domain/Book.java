package org.hell.homework05.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Book {
    private final long id;
    private final String author;
    private final String title;
}
