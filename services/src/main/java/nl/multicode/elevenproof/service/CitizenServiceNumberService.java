package nl.multicode.elevenproof.service;

import lombok.RequiredArgsConstructor;
import nl.multicode.elevenproof.generate.CitizenServiceNumberGenerator;
import nl.multicode.elevenproof.map.StringToIntArray;
import nl.multicode.elevenproof.model.CitizenServiceNumberDto;
import nl.multicode.elevenproof.validate.CitizenServiceNumberElevenProof;

@RequiredArgsConstructor
public class CitizenServiceNumberService implements ElevenProofService<CitizenServiceNumberDto> {

    private final CitizenServiceNumberGenerator generator;

    private final CitizenServiceNumberElevenProof elevenProof;

    private final StringToIntArray stringToIntArray;

    @Override
    public CitizenServiceNumberDto generate() {

        return CitizenServiceNumberDto.builder()
                .number(generator.generate())
                .build();
    }

    @Override
    public boolean isValid(String number) {

        final var bsnDigits = stringToIntArray.apply(number);
        return elevenProof.test(bsnDigits);
    }
}
