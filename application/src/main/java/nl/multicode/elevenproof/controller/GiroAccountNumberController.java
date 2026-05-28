package nl.multicode.elevenproof.controller;

import lombok.RequiredArgsConstructor;
import nl.multicode.elevenproof.openapi.model.GiroAccountNumber;
import nl.multicode.elevenproof.service.ValidateOnlyService;

/**
 * Controller that adapts {@link ValidateOnlyService} responses into the OpenAPI-generated
 * {@link GiroAccountNumber} model. Only validation is exposed — Giro numbers cannot be
 * meaningfully generated.
 */
@RequiredArgsConstructor
public class GiroAccountNumberController implements ValidateOnlyController<GiroAccountNumber> {

    private final ValidateOnlyService service;

    /**
     * Validates the supplied Giro account number and returns the input together with the
     * validation verdict.
     *
     * @param number the Giro account number to validate
     * @return a {@link GiroAccountNumber} carrying the input number and the validation result
     */
    @Override
    public GiroAccountNumber validate(String number) {

        return GiroAccountNumber.builder()
                .number(number)
                .isValid(service.isValid(number))
                .build();
    }
}
