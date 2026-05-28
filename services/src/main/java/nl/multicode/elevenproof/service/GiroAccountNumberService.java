package nl.multicode.elevenproof.service;

import lombok.RequiredArgsConstructor;
import nl.multicode.elevenproof.map.StringToIntArray;
import nl.multicode.elevenproof.validate.GiroAccountNumberElevenProof;

@RequiredArgsConstructor
public class GiroAccountNumberService implements ElevenProofService<Void> {

    private final GiroAccountNumberElevenProof elevenProof;
    private final StringToIntArray stringToIntArray;

    @Override
    public Void generate() {
        throw new UnsupportedOperationException(
            "Giro account numbers cannot be generated as any 1-7 digit number is technically valid."
        );
    }

    @Override
    public boolean isValid(String number) {
        final var digits = stringToIntArray.apply(number);
        return elevenProof.test(digits);
    }
}
