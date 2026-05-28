package nl.multicode.elevenproof.controller;

/**
 * Controller contract for identifier types that support validation only — no generation.
 *
 * @param <T> the result type produced by {@link #validate(String)}
 */
public interface ValidateOnlyController<T> {

    /**
     * Validates the supplied number and returns a result describing its validity.
     *
     * @param number the number to validate
     * @return a result describing whether the number is valid
     */
    T validate(String number);
}
