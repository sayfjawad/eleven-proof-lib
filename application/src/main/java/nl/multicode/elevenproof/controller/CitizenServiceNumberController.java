package nl.multicode.elevenproof.controller;

import lombok.RequiredArgsConstructor;
import nl.multicode.elevenproof.model.CitizenServiceNumberDto;
import nl.multicode.elevenproof.openapi.model.CitizenServiceNumber;
import nl.multicode.elevenproof.service.ElevenProofService;


/**
 * Controller that adapts {@link ElevenProofService} responses into the OpenAPI-generated
 * {@link CitizenServiceNumber} model exposed at the application boundary.
 */
@RequiredArgsConstructor
public class CitizenServiceNumberController implements
        ElevenproofController<CitizenServiceNumber> {

    private final ElevenProofService<CitizenServiceNumberDto> service;

    /**
     * Generates a new valid BSN.
     *
     * @return a populated {@link CitizenServiceNumber}
     */
    public CitizenServiceNumber generate() {

        return CitizenServiceNumber.builder().number(service.generate().number()).build();
    }

    /**
     * Validates the supplied BSN and returns the input together with the eleven-proof verdict.
     *
     * @param number the BSN to validate
     * @return a {@link CitizenServiceNumber} carrying the input number and the validation result
     */
    public CitizenServiceNumber validate(String number) {

        return CitizenServiceNumber.builder().number(number).isElevenproof(service.isValid(number))
                .build();
    }
}
