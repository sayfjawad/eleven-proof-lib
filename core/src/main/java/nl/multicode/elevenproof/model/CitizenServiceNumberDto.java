package nl.multicode.elevenproof.model;

import lombok.Builder;

@Builder
public record CitizenServiceNumberDto(String number) implements ElevenproofNumber {

}
