package nl.multicode.elevenproof.service;

import lombok.RequiredArgsConstructor;
import nl.multicode.elevenproof.map.StringToIntArray;
import nl.multicode.elevenproof.validate.GiroAccountNumberElevenProof;

/**
 * Service facade for Dutch Giro/Postbank account numbers. Generation is deliberately not
 * supported because every 1-to-7 digit value is a valid Giro format and there is no meaningful
 * way to "generate" one.
 */
@RequiredArgsConstructor
public class GiroAccountNumberService implements ElevenProofService<Void> {

    private final GiroAccountNumberElevenProof elevenProof;
    private final StringToIntArray stringToIntArray;

    /**
     * Generation is not supported for Giro account numbers.
     *
     * @return never returns normally
     * @throws UnsupportedOperationException always
     */
    @Override
    public Void generate() {
        throw new UnsupportedOperationException(
            "Giro account numbers cannot be generated as any 1-7 digit number is technically valid."
        );
    }

    /**
     * Validates the supplied Giro account number string against the Giro length rule.
     *
     * @param number the Giro account number to validate
     * @return {@code true} if {@code number} is between 1 and 7 digits long, {@code false} otherwise
     */
    @Override
    public boolean isValid(String number) {
        final var digits = stringToIntArray.apply(number);
        return elevenProof.test(digits);
    }
}
