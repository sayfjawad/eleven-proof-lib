package nl.multicode.elevenproof.validate;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BankAccountNumberDtoElevenProofTest {

    BankAccountNumberElevenProof elevenProof;

    @BeforeEach
    public void setup() {

        elevenProof = new BankAccountNumberElevenProof();
    }

    @Test
    void isElevenProof_10Digits() {

        assertThat(elevenProof.test(new int[]{0, 4, 8, 4, 8, 4, 8, 4, 8, 8})).isTrue();
    }

    @Test
    void isElevenProof_9Digits() {

        // 1*9 + 2*8 + 3*7 + 4*6 + 5*5 + 6*4 + 7*3 + 8*2 + 2*1 = 9+16+21+24+25+24+21+16+2 = 158
        // 158 % 11 = 4. (Not valid)
        // Let's find a valid one: 12345678x
        // 1*9 + 2*8 + 3*7 + 4*6 + 5*5 + 6*4 + 7*3 + 8*2 = 156
        // 156 % 11 = 2. We need 156 + x to be divisible by 11.
        // 156 + 6 = 162. 162 / 11 = 14.72
        // Next multiple of 11 is 11 * 15 = 165. 165 - 156 = 9.
        // So 123456789: 156 + 9 = 165. 165 % 11 = 0.
        assertThat(elevenProof.test(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9})).isTrue();
    }

    @Test
    void isElevenProof_TooShort() {

        assertThat(elevenProof.test(new int[]{1, 2, 3, 4, 5, 6, 7, 8})).isFalse();
    }

    @Test
    void isElevenProof_TooLong() {

        assertThat(elevenProof.test(new int[]{0, 4, 8, 4, 8, 4, 8, 4, 8, 8, 9})).isFalse();
    }

    @Test
    void isElevenProof_Null() {

        assertThat(elevenProof.test(null)).isFalse();
    }

    @Test
    void isNotElevenProof() {

        assertThat(elevenProof.test(new int[]{1, 2, 3, 4, 8, 8, 8, 8, 8})).isFalse();
        assertThat(elevenProof.test(new int[]{1, 2, 3, 4, 8, 8, 8, 8, 8, 9})).isFalse();
    }
}