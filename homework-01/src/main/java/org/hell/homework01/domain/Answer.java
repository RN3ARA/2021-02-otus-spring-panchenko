package org.hell.homework01.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Answer {
    private final String text;
    private final boolean correct;
}
