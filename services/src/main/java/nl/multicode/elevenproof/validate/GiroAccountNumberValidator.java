package nl.multicode.elevenproof.validate;

/**
 * Validates Dutch Giro/Postbank account numbers by length only.
 * <p>
 * Historically, the former Postbank (now part of ING) used account numbers
 * (girorekeningnummers) that did NOT follow the Eleven Proof (elfproef). These numbers range
 * from 1 to 7 digits; every number within that range is considered a valid Giro format.
 * </p>
 */
public class GiroAccountNumberValidator implements ElevenProof {

    private static final int MIN_LENGTH = 1;
    private static final int MAX_LENGTH = 7;

    /**
     * Tests whether the supplied digits form a valid Giro/Postbank account number. Only the
     * length is checked — the eleven-proof rule is not applied.
     *
     * @param accountNr the Giro account digits to test
     * @return {@code true} if {@code accountNr} has between 1 and 7 digits, {@code false} otherwise
     */
    @Override
    public boolean test(int[] accountNr) {

        return accountNr != null
                && accountNr.length >= MIN_LENGTH
                && accountNr.length <= MAX_LENGTH;
    }
}
