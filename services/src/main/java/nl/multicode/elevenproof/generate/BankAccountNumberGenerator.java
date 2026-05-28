package nl.multicode.elevenproof.generate;

import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;
import nl.multicode.elevenproof.generate.supplier.ObjectSupplier;
import nl.multicode.elevenproof.map.IntArrayToString;
import nl.multicode.elevenproof.validate.ElevenProof;

/**
 * Generates Dutch bank account numbers by repeatedly drawing candidate digit sequences from the
 * supplied {@link ObjectSupplier} until one passes the bank-account eleven-proof check.
 */
@RequiredArgsConstructor
public class BankAccountNumberGenerator implements Generator {

    /** Expected digit length for a Dutch (pre-IBAN) bank account number. */
    public static final int BANK_ACCOUNT_DIGITS_LENGTH = 10;

    private final ObjectSupplier<int[]> randomDigitsSupplier;

    private final IntArrayToString intArrayToString;

    private final ElevenProof numberElevenProof;

    /**
     * Generates a candidate digit sequence and returns the first one that satisfies the
     * eleven-proof rule, converted to its string form.
     *
     * @return a valid bank account number, or {@code null} if no candidate was produced
     */
    @Override
    public String generate() {

        return Stream.generate(randomDigitsSupplier::supply)
                .filter(numberElevenProof)
                .map(intArrayToString)
                .findFirst()
                .orElse(null);
    }
}
