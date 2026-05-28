package nl.multicode.elevenproof.controller;

/**
 * Top-level controller contract for entry-point handlers that orchestrate eleven-proof
 * generation and validation flows.
 *
 * @param <T> the controller-specific result type produced by {@link #generate()} and
 *            {@link #validate(String)}
 */
public interface ElevenproofController<T> {

    /**
     * Generates a new valid eleven-proof number.
     *
     * @return the generated result
     */
    T generate();

    /**
     * Validates the supplied number against the eleven-proof rule.
     *
     * @param number the number to validate
     * @return a result describing whether the number is valid
     */
    T validate(String number);
}
