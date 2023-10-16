package model;

/**
 * This exception is thrown when an invalid color character is provided.
 * An invalid color character is one that is not a letter of the alphabet.
 */
public class InvalidColorException extends IllegalArgumentException {

    /**
     * Constructs a new InvalidColorException with a default error message.
     * The error message indicates that the provided color character is not a letter of the alphabet.
     */
    public InvalidColorException() {
        super("Invalid color. The color must be a letter of the alphabet.");
    }
}
