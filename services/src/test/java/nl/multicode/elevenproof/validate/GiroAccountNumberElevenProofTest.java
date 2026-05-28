package nl.multicode.elevenproof.validate;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GiroAccountNumberElevenProofTest {

    GiroAccountNumberElevenProof elevenProof;

    @BeforeEach
    public void setup() {

        elevenProof = new GiroAccountNumberElevenProof();
    }

    @Test
    void testValidLengths() {

        assertThat(elevenProof.test(new int[]{1})).isTrue();
        assertThat(elevenProof.test(new int[]{1, 2, 3})).isTrue();
        assertThat(elevenProof.test(new int[]{1, 2, 3, 4, 5, 6, 7})).isTrue();
    }

    @Test
    void testInvalidLengths() {

        assertThat(elevenProof.test(new int[]{})).isFalse();
        assertThat(elevenProof.test(new int[]{1, 2, 3, 4, 5, 6, 7, 8})).isFalse();
    }

    @Test
    void testNull() {

        assertThat(elevenProof.test(null)).isFalse();
    }
}
