package eu.kolontaj.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;

@Getter
@Setter
@Jacksonized
@Builder
public class GuitarNotesDto {

    private final String string1;

    private final String string2;

    private final String string3;

    private final String string4;

    private final String string5;

    private final String string6;

}
