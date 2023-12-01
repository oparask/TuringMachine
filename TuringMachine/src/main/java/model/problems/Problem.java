package model.problems;

import model.Code;

/**
 * The {@code Problem} class represents a problem with a specific number, difficulty level,
 * luck degree, secret code, and an array of validators.
 */
public class Problem {
    private int problemNumber;
    private int difficultyLevel;
    private int luckDegree;
    private Code secretCode;
    private int[] validators;

    /**
     * Constructs a new Problem instance with the specified parameters.
     *
     * @param problemNumber   The number associated with the problem.
     * @param difficultyLevel The difficulty level of the problem.
     * @param luckDegree      The degree of luck associated with the problem.
     * @param secretCode      The secret code associated with the problem.
     * @param validators      An array of validators for the problem.
     */
    public Problem(int problemNumber, int difficultyLevel, int luckDegree, Code secretCode, int[] validators) {
        this.problemNumber = problemNumber;
        this.difficultyLevel = difficultyLevel;
        this.luckDegree = luckDegree;
        this.secretCode = secretCode;
        this.validators = validators;
    }

    /**
     * Gets the problem number.
     *
     * @return The problem number.
     */
    public int getProblemNumber() {
        return problemNumber;
    }

    /**
     * Gets the difficulty level of the problem.
     *
     * @return The difficulty level.
     */
    public int getDifficultyLevel() {
        return difficultyLevel;
    }

    /**
     * Gets the luck degree associated with the problem.
     *
     * @return The luck degree.
     */
    public int getLuckDegree() {
        return luckDegree;
    }

    /**
     * Gets the secret code associated with the problem.
     *
     * @return The secret code.
     */
    public Code getSecretCode() {
        return secretCode;
    }

    /**
     * Gets the array of validators for the problem.
     *
     * @return The array of validators.
     */
    public int[] getValidators() {
        return validators;
    }

    /**
     * Returns a string representation of the problem.
     *
     * @return A string with the problem details.
     */
    @Override
    public String toString() {
        return "Problem #" + problemNumber +
                ", Difficulty Level: " + difficultyLevel +
                ", Luck Degree: " + luckDegree +
                ", Secret Code: " + secretCode +
                ", Validators: " + arrayToString(validators);
    }

    // Utility method to convert an array to a string
    private String arrayToString(int[] array) {
        StringBuilder result = new StringBuilder("[");
        for (int i = 0; i < array.length; i++) {
            result.append(array[i]);
            if (i < array.length - 1) {
                result.append(", ");
            }
        }
        result.append("]");
        return result.toString();
    }
}

