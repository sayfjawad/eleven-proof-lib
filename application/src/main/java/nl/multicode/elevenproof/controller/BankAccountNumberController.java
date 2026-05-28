package nl.multicode.elevenproof.controller;

import lombok.RequiredArgsConstructor;
import nl.multicode.elevenproof.model.BankAccountNumberDto;
import nl.multicode.elevenproof.openapi.model.BankAccountNumber;
import nl.multicode.elevenproof.service.ElevenProofService;


@RequiredArgsConstructor
public class BankAccountNumberController implements
        ElevenproofController<BankAccountNumber> {

    private final ElevenProofService<BankAccountNumberDto> service;

    public BankAccountNumber generate() {

        return BankAccountNumber.builder()
                .number(service.generate().number())
                .build();
    }

    public BankAccountNumber validate(String number) {

        return BankAccountNumber.builder()
                .number(number)
                .isElevenproof(service.isValid(number))
                .build();
    }
}
