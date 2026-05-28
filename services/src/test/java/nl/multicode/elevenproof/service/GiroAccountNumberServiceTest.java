package nl.multicode.elevenproof.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import nl.multicode.elevenproof.map.StringToIntArray;
import nl.multicode.elevenproof.validate.GiroAccountNumberValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class GiroAccountNumberServiceTest {

    @Mock
    private GiroAccountNumberValidator validator;

    @Mock
    private StringToIntArray stringToIntArray;

    @InjectMocks
    private GiroAccountNumberService service;

    @Test
    void isValid_returns_true_for_valid_giro_number() {

        final var digits = new int[]{1, 2, 3};
        final var number = "123";
        when(stringToIntArray.apply(number)).thenReturn(digits);
        when(validator.test(digits)).thenReturn(true);

        assertThat(service.isValid(number)).isTrue();
        verify(stringToIntArray).apply(number);
        verify(validator).test(digits);
    }

    @Test
    void isValid_returns_false_for_invalid_giro_number() {

        final var digits = new int[]{1, 2, 3, 4, 5, 6, 7, 8};
        final var number = "12345678";
        when(stringToIntArray.apply(number)).thenReturn(digits);
        when(validator.test(digits)).thenReturn(false);

        assertThat(service.isValid(number)).isFalse();
        verify(stringToIntArray).apply(number);
        verify(validator).test(digits);
    }
}
