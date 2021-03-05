package org.hell.homework02.dao;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.hell.homework02.domain.Answer;
import org.hell.homework02.domain.Question;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Repository
public class QuestionDaoCsv implements QuestionDao{

    @Value("${questionCsvPath}")
    private ClassPathResource csvResource;

    @Override
    public List<Question> findAll() {
        return readCsvResource();
    }

    private List<Question> readCsvResource() {
        List<Question> questions = new ArrayList<>();
        if (csvResource.exists()) {
            try (CSVReader reader = new CSVReader(new InputStreamReader(csvResource.getInputStream()))) {
                List<String[]> lines = reader.readAll();
                for (String[] line : lines) {
                    Question question = new Question(line[0], new ArrayList<>(), Integer.parseInt(line[5]));
                    IntStream.rangeClosed(1, 4)
                            .forEach(i -> question.getAnswers()
                                    .add(new Answer(line[i])));
                    questions.add(question);
                }
            } catch (IOException | CsvException e) {
                e.printStackTrace();
            }
        }
        return questions;
    }

}
