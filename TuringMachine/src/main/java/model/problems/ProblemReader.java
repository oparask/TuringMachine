package model.problems;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import model.Code;
/**
 * The ProblemReader class is responsible for reading problem data from a CSV file
 * and constructing a list of Problem objects based on the file content.
 *
 * The CSV file is expected to have the following format:
 *   - Column 1: Problem Number (integer)
 *   - Column 2: Difficulty Level (integer)
 *   - Column 3: Luck Degree (integer)
 *   - Column 4: Secret Code (integer)
 *   - Columns 5 onwards: Validator indices (integer)
 */
public class ProblemReader {

    /**
     * The list of Problem objects read from the CSV file.
     */
    private List<Problem> problems;

    /**
     * Constructs a new ProblemReader and reads problem data from the specified CSV file.
     */
    public ProblemReader() {
        problems = new ArrayList<>();
        String csvFilePath = "src/main/resources/known_problems.csv";

        try (CSVReader reader = new CSVReader(new FileReader(csvFilePath))) {
            List<String[]> lines = reader.readAll();  // Read all lines from the file

            // Start from the second line (index 1)
            for (int lineIndex = 1; lineIndex < lines.size(); lineIndex++) {
                String[] line = lines.get(lineIndex);

                int problemNumber = Integer.parseInt(line[0]);
                int difficultyLevel = Integer.parseInt(line[1]);
                int luckDegree = Integer.parseInt(line[2]);
                Code secretCode = new Code(Integer.parseInt(line[3]));

                List<String> validators = new ArrayList<>();
                for (int i = 4; i < line.length; i++) {
                    if (!line[i].isEmpty()) {
                        validators.add(line[i]);
                    } else {
                        break;
                    }
                }

                int[] validatorsArray = validators.stream().mapToInt(Integer::parseInt).toArray();

                Problem problem = new Problem(problemNumber, difficultyLevel, luckDegree, secretCode, validatorsArray);
                problems.add(problem);
            }
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns the list of Problem objects read from the CSV file.
     *
     * @return The list of Problem objects.
     */
    public List<Problem> getProblems() {
        return problems;
    }
}
