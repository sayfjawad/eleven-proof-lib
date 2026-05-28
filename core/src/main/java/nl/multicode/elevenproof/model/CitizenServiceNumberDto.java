package nl.multicode.elevenproof.model;

import lombok.Builder;

/**
 * Immutable value object representing a Dutch citizen service number (BSN) that has been
 * generated or validated against the eleven-proof rule.
 *
 * @param number the BSN as a string of digits
 */
@Builder
public record CitizenServiceNumberDto(String number) implements ElevenproofNumber {

}
