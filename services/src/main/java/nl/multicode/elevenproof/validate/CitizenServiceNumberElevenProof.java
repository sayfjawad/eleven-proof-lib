package nl.multicode.elevenproof.validate;

/**
 * Implementation of the Eleven Proof (elfproef) for the Dutch Citizen Service Number
 * (Burgerservicenummer - BSN).
 * <p>
 * The BSN is a unique personal identification number for everyone who is registered in the
 * Basic Registry of Persons (BRP) in the Netherlands.
 * </p>
 * <p>
 * Business Rules:
 * <ul>
 *   <li>The number consists of 9 digits.</li>
 *   <li>Multipliers are {9, 8, 7, 6, 5, 4, 3, 2, -1}.</li>
 *   <li>The sum of the digits multiplied by their respective weights must be divisible by 11.</li>
 * </ul>
 * </p>
 */
public class CitizenServiceNumberElevenProof implements ElevenProof {

    private static final int[] BSN_ONDNR_MULTIPLIERS = {9, 8, 7, 6, 5, 4, 3, 2, -1};

    @Override
    public boolean test(int[] bsn) {

        return bsn != null &&
                bsn.length == BSN_ONDNR_MULTIPLIERS.length &&
                test(bsn, BSN_ONDNR_MULTIPLIERS);
    }
}
