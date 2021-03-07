package org.hell.homework02.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
public class Question {
    private final String text;
    private final List<Answer> answers;
    private final int correctAnswer;

}
