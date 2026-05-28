package nl.multicode.elevenproof.validate;

import java.util.function.Predicate;
import java.util.stream.IntStream;

/**
 * Core contract for the eleven-proof check. Implementations supply the digit-position multipliers
 * that match the variant under test (BSN, bank account number, giro account number, etc.) and
 * delegate to {@link #test(int[], int[])} for the actual arithmetic.
 */
public interface ElevenProof extends Predicate<int[]> {

    /**
     * Tests whether the supplied digits satisfy the eleven-proof rule for this variant.
     *
     * @param number the digits to test
     * @return {@code true} if the digits pass the eleven-proof, {@code false} otherwise
     */
    boolean test(int[] number);

    /**
     * Tests the supplied digits using a caller-provided multiplier table. Returns {@code false}
     * when either array is {@code null} or their lengths differ.
     *
     * @param number                   the digits to test
     * @param digitPositionMultipliers the multipliers paired with each digit position
     * @return {@code true} if the weighted sum is divisible by eleven, {@code false} otherwise
     */
    default boolean test(int[] number, int[] digitPositionMultipliers) {

        if (isValidInput(number, digitPositionMultipliers)) {
            return isDividableByEleven(getMultiplicationSum(number, digitPositionMultipliers));
        }
        return false;
    }

    private boolean isValidInput(int[] number, int[] digitPositionMultipliers) {

        return number != null && digitPositionMultipliers != null
                && number.length == digitPositionMultipliers.length;
    }

    private int getMultiplicationSum(int[] number, int[] digitPositionMultipliers) {

        return IntStream.range(0, number.length)
                .map(index -> number[index] * digitPositionMultipliers[index])
                .sum();
    }

    private boolean isDividableByEleven(int sum) {

        return sum % 11 == 0;
    }
}
