package nl.multicode.elevenproof.validate;

/**
 * Implementation of the Eleven Proof (elfproef) for Dutch Bank Account numbers.
 * <p>
 * This validator supports both 9 and 10 digit bank account numbers, which were the standard
 * for most Dutch commercial banks (e.g., Rabobank, ABN AMRO, SNS Bank, ASN Bank, Triodos)
 * before the migration to IBAN.
 * </p>
 * <p>
 * Business Rules:
 * <ul>
 *   <li>For 10-digit numbers: Multipliers are {10, 9, 8, 7, 6, 5, 4, 3, 2, 1}.</li>
 *   <li>For 9-digit numbers: Multipliers are {9, 8, 7, 6, 5, 4, 3, 2, 1}.</li>
 *   <li>The sum of the digits multiplied by their respective weights must be divisible by 11.</li>
 * </ul>
 */
public class BankAccountNumberElevenProof implements ElevenProof {

    private static final int[] MULTIPLIERS_10_DIGITS = {10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
    private static final int[] MULTIPLIERS_9_DIGITS = {9, 8, 7, 6, 5, 4, 3, 2, 1};

    /**
     * Tests whether the supplied digits form a valid 9- or 10-digit Dutch bank account number
     * according to the eleven-proof rule.
     *
     * @param accountNr the bank account digits to test
     * @return {@code true} if the digits pass the eleven-proof for the matching length,
     *         {@code false} for {@code null} input or unsupported lengths
     */
    @Override
    public boolean test(int[] accountNr) {

        if (accountNr == null) {
            return false;
        }

        if (accountNr.length == MULTIPLIERS_10_DIGITS.length) {
            return test(accountNr, MULTIPLIERS_10_DIGITS);
        }

        if (accountNr.length == MULTIPLIERS_9_DIGITS.length) {
            return test(accountNr, MULTIPLIERS_9_DIGITS);
        }

        return false;
    }
}
