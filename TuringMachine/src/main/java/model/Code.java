package model;

/**
 * The {@code Code} class represents a three-digit code with each digit ranging from 1 to 5, inclusive.
 */
import java.util.Iterator;

public class Code implements Iterable<Integer> {
    private int firstDigit;
    private int secondDigit;
    private int thirdDigit;

    public Code(int code) {
        int firstDigit = code / 100;
        int secondDigit = (code / 10) % 10;
        int thirdDigit =  code % 10;

        if (!isValidDigit(firstDigit) || !isValidDigit(secondDigit) || !isValidDigit(thirdDigit)) {
            throw new IllegalArgumentException("The numbers must be between 1 and 5, inclusive.");
        } else {
            this.firstDigit = firstDigit;
            this.secondDigit = secondDigit;
            this.thirdDigit = thirdDigit;
        }
    }

    public int getFirstDigit() {
        return firstDigit;
    }

    public int getSecondDigit() {
        return secondDigit;
    }

    public int getThirdDigit() {
        return thirdDigit;
    }

    public int getDigit(int index) {
        if (index < 1 || index > 3) {
            throw new IllegalArgumentException("Index must be between 1 and 3, inclusive.");
        }
        return switch (index) {
            case 1 -> firstDigit;
            case 2 -> secondDigit;
            case 3 -> thirdDigit;
            default -> throw new IllegalStateException("Unexpected value: " + index);
        };
    }
    @Override
    public Iterator<Integer> iterator() {
        return new CodeIterator();
    }

    @Override
    public String toString() {
        return "Code: " + firstDigit + secondDigit + thirdDigit;
    }

    private boolean isValidDigit(int digit) {
        return digit >= 1 && digit <= 5;
    }

    private class CodeIterator implements Iterator<Integer> {
        private int currentIndex = 0;

        @Override
        public boolean hasNext() {
            return currentIndex < 3;
        }

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
}

