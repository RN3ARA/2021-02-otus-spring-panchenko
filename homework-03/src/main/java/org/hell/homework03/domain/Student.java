package org.hell.homework03.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class Student {
    private String firstName;
    private String lastName;
    private List<Integer> answers = new ArrayList<>();
}
