package nl.multicode.elevenproof.generate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.function.Supplier;
import nl.multicode.elevenproof.map.IntArrayToString;
import nl.multicode.elevenproof.validate.ElevenProof;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class BankAccountNumberGeneratorTest {

    @Mock
    private Supplier<int[]> randomDigitsSupplier;

    @Mock
    private IntArrayToString intArrayToString;

    @Mock
    private ElevenProof numberElevenProof;

    @InjectMocks
    private BankAccountNumberGenerator generator;

    @Test
    @DisplayName("Given a valid bank account number, when generate is called, then return a String representation of the bank account number")
    public void testGenerate() {

        final var validBankNumber = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 0};
        when(randomDigitsSupplier.get()).thenReturn(validBankNumber);
        when(numberElevenProof.test(any())).thenReturn(true);
        when(intArrayToString.apply(any())).thenReturn("1234567890");

        final var result = generator.generate();
        assertEquals("1234567890", result);
    }
}
