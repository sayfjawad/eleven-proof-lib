package nl.multicode.elevenproof.map;

import java.util.Arrays;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Converts an array of digits into the concatenated string representation of those digits.
 * For example {@code [1, 2, 3]} is mapped to {@code "123"}.
 */
public class IntArrayToString implements Function<int[], String> {

    /**
     * Concatenates the supplied digits into a single string.
     *
     * @param ints the digits to concatenate
     * @return the digits joined as a string
     */
    @Override
    public String apply(int[] ints) {

        return Arrays.stream(ints)
                .mapToObj(String::valueOf)
                .collect(Collectors.joining());
    }
}
