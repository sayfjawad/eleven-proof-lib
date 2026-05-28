package nl.multicode.elevenproof.controller;

import lombok.RequiredArgsConstructor;
import nl.multicode.elevenproof.model.CitizenServiceNumberDto;
import nl.multicode.elevenproof.openapi.model.CitizenServiceNumber;
import nl.multicode.elevenproof.service.ElevenProofService;


@RequiredArgsConstructor
public class CitizenServiceNumberController implements
        ElevenproofController<CitizenServiceNumber> {

    private final ElevenProofService<CitizenServiceNumberDto> service;

    public CitizenServiceNumber generate() {

        return CitizenServiceNumber.builder().number(service.generate().number()).build();
    }

    public CitizenServiceNumber validate(String number) {

        return CitizenServiceNumber.builder().number(number).isElevenproof(service.isValid(number))
                .build();
    }
}
