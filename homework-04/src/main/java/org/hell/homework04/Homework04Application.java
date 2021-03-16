package org.hell.homework04;

import org.hell.homework04.config.AppProps;
import org.hell.homework04.service.ExamService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
@EnableConfigurationProperties(AppProps.class)
public class Homework04Application {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(Homework04Application.class, args);
        ExamService service = context.getBean(ExamService.class);
        service.start();
    }

}
