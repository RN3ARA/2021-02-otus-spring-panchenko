package org.hell.homework01.dao;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import lombok.Data;
import org.hell.homework01.domain.Answer;
import org.hell.homework01.domain.Question;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Data
public class QuestionDaoCsv implements QuestionDao{
    private Resource csvResource;

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
                    Question question = new Question(line[0], new ArrayList<>());
                    IntStream.rangeClosed(1, 3)
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
