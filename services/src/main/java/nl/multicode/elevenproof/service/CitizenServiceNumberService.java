package nl.multicode.elevenproof.service;

import lombok.RequiredArgsConstructor;
import nl.multicode.elevenproof.generate.CitizenServiceNumberGenerator;
import nl.multicode.elevenproof.map.StringToIntArray;
import nl.multicode.elevenproof.model.CitizenServiceNumberDto;
import nl.multicode.elevenproof.validate.CitizenServiceNumberElevenProof;

/**
 * Service facade for Dutch citizen service numbers (BSN). Combines a generator and the BSN
 * variant of the eleven-proof into a single API for callers that want to generate or validate
 * a BSN without wiring the lower-level components themselves.
 */
@RequiredArgsConstructor
public class CitizenServiceNumberService implements ElevenProofService<CitizenServiceNumberDto> {

    private final CitizenServiceNumberGenerator generator;

    private final CitizenServiceNumberElevenProof elevenProof;

    private final StringToIntArray stringToIntArray;

    /**
     * Generates a new valid BSN wrapped in a {@link CitizenServiceNumberDto}.
     *
     * @return a DTO containing a freshly generated, eleven-proof-compliant BSN
     */
    @Override
    public CitizenServiceNumberDto generate() {

        return CitizenServiceNumberDto.builder()
                .number(generator.generate())
                .build();
    }

    /**
     * Validates the supplied BSN string against the eleven-proof rule.
     *
     * @param number the BSN to validate
     * @return {@code true} if the BSN passes the eleven-proof, {@code false} otherwise
     */
    @Override
    public boolean isValid(String number) {

        final var bsnDigits = stringToIntArray.apply(number);
        return elevenProof.test(bsnDigits);
    }
}
