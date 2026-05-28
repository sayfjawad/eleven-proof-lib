package nl.multicode.elevenproof.service;

import lombok.RequiredArgsConstructor;
import nl.multicode.elevenproof.map.StringToIntArray;
import nl.multicode.elevenproof.validate.GiroAccountNumberValidator;

/**
 * Service facade for Dutch Giro/Postbank account numbers. Only validation is supported —
 * generation is meaningless because every 1-to-7 digit value is a valid Giro format.
 */
@RequiredArgsConstructor
public class GiroAccountNumberService implements ValidateOnlyService {

    private final GiroAccountNumberValidator validator;
    private final StringToIntArray stringToIntArray;

    /**
     * Validates the supplied Giro account number string against the Giro length rule.
     *
     * @param number the Giro account number to validate
     * @return {@code true} if {@code number} is between 1 and 7 digits long, {@code false} otherwise
     */
    @Override
    public boolean isValid(String number) {

        return validator.test(stringToIntArray.apply(number));
    }
}
