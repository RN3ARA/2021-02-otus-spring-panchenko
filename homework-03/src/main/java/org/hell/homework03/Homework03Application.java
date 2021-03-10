package org.hell.homework03;

import org.hell.homework03.service.ExamService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Homework03Application {

    public static void main(String[] args) {
        SpringApplication.run(Homework03Application.class, args);
    }

    @Bean
    public CommandLineRunner testService(ExamService service) {
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                service.start();
            }
        };
    }
}
