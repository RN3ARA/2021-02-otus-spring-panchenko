package org.hell.homework02.config;

import org.hell.homework02.dao.QuestionDao;
import org.hell.homework02.service.QuestionService;
import org.hell.homework02.service.QuestionServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServicesConfig {
    @Bean
    public QuestionService questionService(QuestionDao dao) {
        return new QuestionServiceImpl(dao);
    }
}
