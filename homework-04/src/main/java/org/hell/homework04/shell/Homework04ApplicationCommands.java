package org.hell.homework04.shell;

import lombok.RequiredArgsConstructor;
import org.hell.homework04.service.ExamService;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
@RequiredArgsConstructor
public class Homework04ApplicationCommands {
    private final ExamService examService;

    @ShellMethod(value = "Start command", key = {"s", "start"})
    public void start() {
        examService.start();
    }
}
