package org.hell.homework05.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Author {
    private final long id;
    private final String firstName;
    private final String lastName;
}
