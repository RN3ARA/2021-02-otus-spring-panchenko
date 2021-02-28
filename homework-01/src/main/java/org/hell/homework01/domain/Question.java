package org.hell.homework01.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
public class Question {
    private final String text;
    private final List<Answer> answers;
}
