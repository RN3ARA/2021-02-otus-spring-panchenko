package org.hell.homework02;

import org.hell.homework02.domain.Answer;
import org.hell.homework02.domain.Question;
import org.hell.homework02.service.QuestionService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@ComponentScan
public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Main.class);
        QuestionService service = context.getBean(QuestionService.class);
        List<Question> questions = service.getAll();
        for (Question question : questions) {
            System.out.println(question.getText());
            for (Answer answer : question.getAnswers()) {
                System.out.println(answer.getText());
            }
        }

        }
}
