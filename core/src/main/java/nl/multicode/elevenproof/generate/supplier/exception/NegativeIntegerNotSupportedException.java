package nl.multicode.elevenproof.generate.supplier.exception;

/**
 * Thrown when a negative integer is provided to a component that only accepts non-negative input,
 * such as a digit-count argument for a random-number supplier.
 */
public class NegativeIntegerNotSupportedException extends RuntimeException {

    /**
     * Creates a new exception with a fixed explanatory message.
     */
    public NegativeIntegerNotSupportedException() {

        super("Negative integers are not accepted nor supported arguments.");
    }
}
