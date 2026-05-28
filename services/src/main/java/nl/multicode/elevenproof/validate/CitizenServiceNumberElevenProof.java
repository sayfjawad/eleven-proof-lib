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
 */
public class CitizenServiceNumberElevenProof implements ElevenProof {

    private static final int[] BSN_ONDNR_MULTIPLIERS = {9, 8, 7, 6, 5, 4, 3, 2, -1};

    /**
     * Tests whether the supplied digits form a valid 9-digit Dutch BSN according to the
     * eleven-proof rule with the BSN-specific multiplier table.
     *
     * @param bsn the BSN digits to test
     * @return {@code true} if the digits pass the BSN eleven-proof, {@code false} for
     *         {@code null} input or an incorrect length
     */
    @Override
    public boolean test(int[] bsn) {

        return bsn != null &&
                bsn.length == BSN_ONDNR_MULTIPLIERS.length &&
                test(bsn, BSN_ONDNR_MULTIPLIERS);
    }
}
