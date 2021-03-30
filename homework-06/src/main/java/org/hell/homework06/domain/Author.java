package org.hell.homework06.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Author {
    private long id;
    private final String firstName;
    private final String lastName;
}
