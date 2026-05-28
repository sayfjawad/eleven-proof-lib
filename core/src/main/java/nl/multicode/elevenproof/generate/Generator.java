package nl.multicode.elevenproof.generate;

/**
 * Contract for generators that produce a string representation of a number that satisfies the
 * eleven-proof rule (for instance a BSN or a bank account number).
 */
public interface Generator {

    /**
     * Generates a new eleven-proof-compliant number.
     *
     * @return the generated number as a string of digits
     */
    String generate();
}
