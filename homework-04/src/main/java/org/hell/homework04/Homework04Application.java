package org.hell.homework04;

import org.hell.homework04.config.AppProps;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(AppProps.class)
public class Homework04Application {

    public static void main(String[] args) {
        SpringApplication.run(Homework04Application.class, args);
    }

}
