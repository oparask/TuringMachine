package model.problems;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import com.opencsv.exceptions.CsvValidationException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import model.Code;

public class ProblemReader {
    List<Problem> problems;

    public ProblemReader() {

        problems = new ArrayList<>();
        String csvFilePath = "src/main/resources/known_problems.csv";

        try (CSVReader reader = new CSVReader(new FileReader(csvFilePath))) {
            List<String[]> lines = reader.readAll();  // Lire toutes les lignes du fichier

            // Commencer à partir de la deuxième ligne (indice 1)
            for (int lineIndex = 1; lineIndex < lines.size(); lineIndex++) {
                String[] line = lines.get(lineIndex);

                int problemNumber = Integer.parseInt(line[0]);
                int difficultyLevel = Integer.parseInt(line[1]);
                int luckDegree = Integer.parseInt(line[2]);
                Code secretCode = new Code(Integer.parseInt(line[3]));

                String[] stringValidators = line[4].split(",");
                int[] validators = new int[stringValidators.length];
                for (int i = 0; i < stringValidators.length; i++) {
                    validators[i] = Integer.parseInt(stringValidators[i].trim());
                }

                Problem problem = new Problem(problemNumber, difficultyLevel, luckDegree, secretCode, validators);
                problems.add(problem);
            }
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }

    }


    public List<Problem> getProblems() {
        return problems;
    }
}

