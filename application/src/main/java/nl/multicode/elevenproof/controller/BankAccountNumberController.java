package nl.multicode.elevenproof.controller;

import lombok.RequiredArgsConstructor;
import nl.multicode.elevenproof.model.BankAccountNumberDto;
import nl.multicode.elevenproof.openapi.model.BankAccountNumber;
import nl.multicode.elevenproof.service.ElevenProofService;


/**
 * Controller that adapts {@link ElevenProofService} responses into the OpenAPI-generated
 * {@link BankAccountNumber} model exposed at the application boundary.
 */
@RequiredArgsConstructor
public class BankAccountNumberController implements
        ElevenproofController<BankAccountNumber> {

    private final ElevenProofService<BankAccountNumberDto> service;

    /**
     * Generates a new valid bank account number.
     *
     * @return a populated {@link BankAccountNumber}
     */
    public BankAccountNumber generate() {

        return BankAccountNumber.builder()
                .number(service.generate().number())
                .build();
    }

    /**
     * Validates the supplied bank account number and returns the input together with the
     * eleven-proof verdict.
     *
     * @param number the bank account number to validate
     * @return a {@link BankAccountNumber} carrying the input number and the validation result
     */
    public BankAccountNumber validate(String number) {

        return BankAccountNumber.builder()
                .number(number)
                .isElevenproof(service.isValid(number))
                .build();
    }
}
