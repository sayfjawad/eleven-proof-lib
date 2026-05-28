package nl.multicode.elevenproof.validate;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GiroAccountNumberValidatorTest {

    GiroAccountNumberValidator validator;

    @BeforeEach
    public void setup() {

        validator = new GiroAccountNumberValidator();
    }

    @Test
    void testValidLengths() {

        assertThat(validator.test(new int[]{1})).isTrue();
        assertThat(validator.test(new int[]{1, 2, 3})).isTrue();
        assertThat(validator.test(new int[]{1, 2, 3, 4, 5, 6, 7})).isTrue();
    }

    @Test
    void testInvalidLengths() {

        assertThat(validator.test(new int[]{})).isFalse();
        assertThat(validator.test(new int[]{1, 2, 3, 4, 5, 6, 7, 8})).isFalse();
    }

    @Test
    void testNull() {

        assertThat(validator.test(null)).isFalse();
    }
}
