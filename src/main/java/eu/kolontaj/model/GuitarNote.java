package eu.kolontaj.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class GuitarNote {

    private GuitarString string;

    private Integer fret;

}
