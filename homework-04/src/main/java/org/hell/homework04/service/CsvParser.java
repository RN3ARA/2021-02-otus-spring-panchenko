package org.hell.homework04.service;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.hell.homework04.config.AppProps;
import org.hell.homework04.domain.Answer;
import org.hell.homework04.domain.Question;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Service
public class CsvParser {
    private final AppProps appProps;

    public CsvParser(AppProps appProps) {
        this.appProps = appProps;
    }

    public List<Question> readCsvResource() {
        List<Question> questions = new ArrayList<>();
        List<String[]> lines = getLinesFromCsv();
        if (lines != null) {
            getQuestions(questions, lines);
        }

        return questions;
    }

    private void getQuestions(List<Question> questions, List<String[]> lines) {
        for (String[] line : lines) {
            Question question = new Question(line[0], new ArrayList<>(), Integer.parseInt(line[5]));
            getAnswers(line, question);
            questions.add(question);
        }
    }

    private void getAnswers(String[] line, Question question) {
        IntStream.rangeClosed(1, 4)
                .forEach(i -> question.getAnswers()
                        .add(new Answer(line[i])));
    }

    private List<String[]> getLinesFromCsv() {
        ClassPathResource csvResource = new ClassPathResource(appProps.getQuestionCsvPath());
        if (csvResource.exists()) {
            try (CSVReader reader = new CSVReader(new InputStreamReader(csvResource.getInputStream()))) {
                return new ArrayList<>(reader.readAll());
            } catch (IOException | CsvException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

}
