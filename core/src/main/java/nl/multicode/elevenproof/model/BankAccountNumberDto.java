package nl.multicode.elevenproof.model;

import lombok.Builder;

/**
 * Immutable value object representing a Dutch bank account number that has been generated or
 * validated against the eleven-proof rule.
 *
 * @param number the bank account number as a string of digits
 */
@Builder
public record BankAccountNumberDto(String number) implements ElevenproofNumber {

}
