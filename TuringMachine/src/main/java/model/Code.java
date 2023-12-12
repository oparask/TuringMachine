package model;

import java.util.Iterator;
import java.util.Objects;

/**
 * The {@code Code} class represents a three-digit code with each digit ranging from 1 to 5, inclusive.
 * It provides methods to access individual digits, iterate over digits, and validate the code.
 */
public class Code implements Iterable<Integer> {
    private final int firstDigit;
    private final int secondDigit;
    private final int thirdDigit;

    /**
     * Constructs a Code object from a three-digit integer code.
     *
     * @param code The three-digit code to initialize the Code object.
     * @throws IllegalArgumentException if any digit is not between 1 and 5, inclusive.
     */
    public Code(int code) {
        int firstDigit = code / 100;
        int secondDigit = (code / 10) % 10;
        int thirdDigit = code % 10;

        if (!isValidDigit(firstDigit) || !isValidDigit(secondDigit) || !isValidDigit(thirdDigit)) {
            throw new IllegalArgumentException("Each digit must be between 1 and 5, inclusive.");
        } else {
            this.firstDigit = firstDigit;
            this.secondDigit = secondDigit;
            this.thirdDigit = thirdDigit;
        }
    }

    /**
     * Gets the first digit of the code.
     *
     * @return The first digit.
     */
    public int getFirstDigit() {
        return firstDigit;
    }

    /**
     * Gets the second digit of the code.
     *
     * @return The second digit.
     */
    public int getSecondDigit() {
        return secondDigit;
    }

    /**
     * Gets the third digit of the code.
     *
     * @return The third digit.
     */
    public int getThirdDigit() {
        return thirdDigit;
    }

    /**
     * Validates if a digit is within the valid range (1 to 5, inclusive).
     *
     * @param digit The digit to validate.
     * @return {@code true} if the digit is valid, {@code false} otherwise.
     */
    private boolean isValidDigit(int digit) {
        return digit >= 1 && digit <= 5;
    }

    /**
     * Returns an iterator over the digits of the code.
     *
     * @return An iterator.
     */
    @Override
    public Iterator<Integer> iterator() {
        return new CodeIterator();
    }

    /**
     * Iterator for iterating over the digits of the code.
     */
    private class CodeIterator implements Iterator<Integer> {
        private int currentIndex = 0;

        /**
         * Checks if there is a next digit.
         *
         * @return {@code true} if there is a next digit, {@code false} otherwise.
         */
        @Override
        public boolean hasNext() {
            return currentIndex < 3;
        }

        /**
         * Returns the next digit.
         *
         * @return The next digit.
         * @throws java.util.NoSuchElementException if there is no next digit.
         */
        @Override
        public Integer next() {
            if (!hasNext()) {
                throw new java.util.NoSuchElementException();
            }
            int digit = switch (currentIndex) {
                case 0 -> firstDigit;
                case 1 -> secondDigit;
                case 2 -> thirdDigit;
                default -> throw new IllegalStateException("Unexpected value: " + currentIndex);
            };
            currentIndex++;
            return digit;
        }
    }

    /**
     * Indicates whether some other object is "equal to" this code.
     *
     * @param o The reference object with which to compare.
     * @return {@code true} if this code is the same as the obj argument; {@code false} otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Code)) return false;
        Code otherCode = (Code) o;
        return firstDigit == otherCode.firstDigit && secondDigit == otherCode.secondDigit && thirdDigit == otherCode.thirdDigit;
    }

    /**
     * Returns a hash code value for the code.
     *
     * @return A hash code value for this object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(firstDigit, secondDigit, thirdDigit);
    }


    /**
     * Returns a string representation of the code.
     *
     * @return A string representation.
     */
    @Override
    public String toString() {
        return "" + firstDigit + secondDigit + thirdDigit;
    }
}
