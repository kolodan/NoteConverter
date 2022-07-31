package eu.kolontaj.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

@Getter
@Jacksonized
@Builder
public class NotesRequestDto {

    private final String notes;

}
