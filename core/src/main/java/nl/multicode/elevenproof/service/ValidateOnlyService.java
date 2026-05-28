package nl.multicode.elevenproof.service;

/**
 * Service contract for identifier types that support validation but not generation.
 */
public interface ValidateOnlyService {

    /**
     * Tests whether the supplied number is valid according to the type-specific rule.
     *
     * @param number the number to test
     * @return {@code true} if {@code number} is valid, {@code false} otherwise
     */
    boolean isValid(String number);
}
