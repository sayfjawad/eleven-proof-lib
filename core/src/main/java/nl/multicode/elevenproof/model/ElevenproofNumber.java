package nl.multicode.elevenproof.model;

/**
 * Common contract for value objects that wrap a numeric string subject to the eleven-proof rule.
 * Implementations include {@link BankAccountNumberDto} and {@link CitizenServiceNumberDto}.
 */
public interface ElevenproofNumber {

    /**
     * Returns the wrapped number as a string of digits.
     *
     * @return the number value
     */
    String number();
}
