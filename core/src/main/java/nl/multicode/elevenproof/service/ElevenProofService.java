package nl.multicode.elevenproof.service;

/**
 * Service contract for generating and validating numbers that must satisfy the eleven-proof rule.
 *
 * @param <T> the value type produced by {@link #generate()} (for example a DTO wrapping the
 *            generated number; {@link Void} for variants where generation is not supported)
 */
public interface ElevenProofService<T> {

    /**
     * Generates a new eleven-proof-compliant value.
     *
     * @return the generated value
     */
    T generate();

    /**
     * Tests whether the supplied number satisfies the eleven-proof rule.
     *
     * @param number the number to test
     * @return {@code true} if {@code number} is valid, {@code false} otherwise
     */
    boolean isValid(String number);
}
