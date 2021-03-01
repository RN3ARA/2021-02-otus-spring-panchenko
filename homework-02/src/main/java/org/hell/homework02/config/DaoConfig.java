package org.hell.homework02.config;

import org.hell.homework02.dao.QuestionDao;
import org.hell.homework02.dao.QuestionDaoCsv;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.util.Objects;

@Configuration
@PropertySource(value = "classpath:dao.properties")
public class DaoConfig {
    private final Environment env;

    public DaoConfig(Environment env) {
        this.env = env;
    }

    @Bean
    public QuestionDao questionDao() {
        Resource resource = new ClassPathResource(Objects.requireNonNull(env.getProperty("questionCsvPath")));
        return new QuestionDaoCsv(resource);
    }
}
