package nl.multicode.elevenproof.controller;

import lombok.RequiredArgsConstructor;
import nl.multicode.elevenproof.openapi.model.GiroAccountNumber;
import nl.multicode.elevenproof.service.GiroAccountNumberService;


@RequiredArgsConstructor
public class GiroAccountNumberController {

    private final GiroAccountNumberService service;

    public GiroAccountNumber validate(String number) {

        return GiroAccountNumber.builder()
                .number(number)
                .isValid(service.isValid(number))
                .build();
    }
}
