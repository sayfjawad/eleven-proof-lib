package nl.multicode.elevenproof.generate.supplier;

import java.util.Random;
import nl.multicode.elevenproof.generate.supplier.exception.NegativeIntegerNotSupportedException;

/**
 * Supplies an array of random single-digit integers of a fixed length. Used by generators that
 * need a candidate sequence of digits to apply the eleven-proof rule against.
 */
public class FixedLengthStringRandomNumbersSupplier implements ObjectSupplier<int[]> {

    /** Exclusive upper bound for each generated digit. */
    public static final int SINGLE_DIGIT_MAX_RANDOM_NUMBER = 9;
    private static final Random RANDOM = new Random();
    private final int digitsLength;

    /**
     * Creates a supplier that produces arrays of the given length.
     *
     * @param digitsLength the number of digits to produce on each {@link #supply()} call;
     *                     must be non-negative
     * @throws NegativeIntegerNotSupportedException if {@code digitsLength} is negative
     */
    public FixedLengthStringRandomNumbersSupplier(int digitsLength) {

        if (digitsLength < 0) {
            throw new NegativeIntegerNotSupportedException();
        }
        this.digitsLength = digitsLength;
    }

    @Override
    public int[] supply() {

        final var randomDigitsArray = new int[digitsLength];
        for (int index = 0; index < digitsLength; index++) {
            randomDigitsArray[index] = RANDOM.nextInt(SINGLE_DIGIT_MAX_RANDOM_NUMBER);
        }
        return randomDigitsArray;
    }
}
