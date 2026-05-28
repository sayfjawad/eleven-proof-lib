package nl.multicode.elevenproof.service;

import lombok.RequiredArgsConstructor;
import nl.multicode.elevenproof.generate.BankAccountNumberGenerator;
import nl.multicode.elevenproof.map.StringToIntArray;
import nl.multicode.elevenproof.model.BankAccountNumberDto;
import nl.multicode.elevenproof.validate.BankAccountNumberElevenProof;

/**
 * Service facade for Dutch bank account numbers. Combines a generator and the bank-account
 * variant of the eleven-proof into a single API for callers that want to generate or validate
 * a number without wiring the lower-level components themselves.
 */
@RequiredArgsConstructor
public class BankAccountNumberService implements ElevenProofService<BankAccountNumberDto> {

    private final BankAccountNumberGenerator generator;

    private final BankAccountNumberElevenProof elevenProof;

    private final StringToIntArray stringToIntArray;

    /**
     * Generates a new valid bank account number wrapped in a {@link BankAccountNumberDto}.
     *
     * @return a DTO containing a freshly generated, eleven-proof-compliant bank account number
     */
    @Override
    public BankAccountNumberDto generate() {

        return BankAccountNumberDto.builder()
                .number(generator.generate())
                .build();
    }

    /**
     * Validates the supplied bank account number string against the eleven-proof rule.
     *
     * @param number the bank account number to validate
     * @return {@code true} if the number passes the eleven-proof, {@code false} otherwise
     */
    @Override
    public boolean isValid(String number) {

        final var bsnDigits = stringToIntArray.apply(number);
        return elevenProof.test(bsnDigits);
    }
}
