package nl.multicode.elevenproof.validate;

/**
 * Implementation of the validation for Dutch Giro/Postbank account numbers.
 * <p>
 * Historically, the former Postbank (now part of ING) used account numbers (girorekeningnummers)
 * that did NOT follow the standard Eleven Proof (elfproef). These numbers range from 1 to 7 digits.
 * </p>
 * <p>
 * Business Rules:
 * <ul>
 *   <li>The number must have between 1 and 7 digits.</li>
 *   <li>No Eleven Proof (modulo-11) is applied. All numbers within this length are considered valid
 *   formats for a Giro number.</li>
 * </ul>
 */
public class GiroAccountNumberElevenProof implements ElevenProof {

    private static final int MIN_LENGTH = 1;
    private static final int MAX_LENGTH = 7;

    @Override
    public boolean test(int[] accountNr) {

        return accountNr != null &&
                accountNr.length >= MIN_LENGTH &&
                accountNr.length <= MAX_LENGTH;
    }
}
