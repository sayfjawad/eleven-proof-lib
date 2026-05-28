package nl.multicode.elevenproof.generate;

import java.util.function.Supplier;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;
import nl.multicode.elevenproof.map.IntArrayToString;
import nl.multicode.elevenproof.validate.ElevenProof;

/**
 * Generates Dutch citizen service numbers (BSN) by repeatedly drawing candidate digit sequences
 * from the supplied {@link Supplier} until one passes the BSN eleven-proof check.
 */
@RequiredArgsConstructor
public class CitizenServiceNumberGenerator implements Generator {

    /** Expected digit length for a Dutch BSN. */
    public static final int BSN_DIGITS_LENGTH = 9;

    private final Supplier<int[]> randomDigitsSupplier;

    private final IntArrayToString intArrayToString;

    private final ElevenProof numberElevenProof;

    /**
     * Generates a candidate digit sequence and returns the first one that satisfies the
     * eleven-proof rule, converted to its string form.
     *
     * @return a valid BSN string
     * @throws IllegalStateException if no valid candidate could be produced
     */
    @Override
    public String generate() {

        return Stream.generate(randomDigitsSupplier)
                .filter(numberElevenProof)
                .map(intArrayToString)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Could not generate a valid citizen service number"));
    }
}
