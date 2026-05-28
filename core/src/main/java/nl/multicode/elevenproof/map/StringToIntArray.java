package nl.multicode.elevenproof.map;

import java.util.function.Function;

/**
 * Converts a numeric string into an array of its digits.
 * For example {@code "123"} is mapped to {@code [1, 2, 3]}.
 */
public class StringToIntArray implements Function<String, int[]> {

    /**
     * Splits the supplied string into an array of integer digits.
     *
     * @param number the numeric string to split
     * @return the digits as an {@code int[]}
     */
    @Override
    public int[] apply(String number) {

        return number.chars()
                .map(Character::getNumericValue)
                .toArray();
    }
}
