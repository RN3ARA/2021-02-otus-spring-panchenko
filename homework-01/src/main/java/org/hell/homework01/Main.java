package org.hell.homework01;

import org.hell.homework01.domain.Answer;
import org.hell.homework01.domain.Question;
import org.hell.homework01.service.QuestionService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/spring-context.xml");
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
