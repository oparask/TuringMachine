package model;

import java.util.ArrayList;
import java.util.List;

/**
 * The {@code Code} class represents a three-digit code with each digit ranging from 1 to 5, inclusive.
 */
public class Code {
    private int digit1;
    private int digit2;
    private int digit3;

    /**
     * Constructs a new Code instance with the specified single integer.
     *
     * @param code The integer to be divided into digits for the code.
     * @throws IllegalArgumentException If any of the digits is not between 1 and 5, inclusive.
     */
    public Code(int code) {
        int firstDigit = code / 100;
        int secondDigit = (code / 10) % 10;
        int thirdDigit =  code % 10;

        if (!isValidDigit(firstDigit) || !isValidDigit(secondDigit) || !isValidDigit(thirdDigit)) {
            throw new IllegalArgumentException("The numbers must be between 1 and 5, inclusive.");
        } else {
            this.digit1 = firstDigit;
            this.digit2 = secondDigit;
            this.digit3 = thirdDigit;
        }

    }

    /**
     * Returns a string representation of the code.
     *
     * @return A string in the format "Code: [digit1][digit2][digit3]".
     */
    @Override
    public String toString() {
        return "Code: " + digit1 + digit2 + digit3;
    }

    /**
     * Checks if a given digit is valid (between 1 and 5, inclusive).
     *
     * @param digit The digit to be validated.
     * @return {@code true} if the digit is valid, {@code false} otherwise.
     */
    private boolean isValidDigit(int digit) {
        return digit >= 1 && digit <= 5;
    }


}
