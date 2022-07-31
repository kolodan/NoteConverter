package eu.kolontaj.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Getter
@RequiredArgsConstructor
public enum GuitarString {

    S1(1), S2(2), S3(3), S4(4), S5(5), S6(6);

    private static final Map<Integer, GuitarString> LOOKUP;

    static {
        LOOKUP = Collections.unmodifiableMap(Arrays.stream(GuitarString.values())
                .collect(Collectors.toMap(GuitarString::getVal, Function.identity())));
    }

    private final int val;

    public static GuitarString lockup(int val){
        return Optional.ofNullable(LOOKUP.get(val))
                .orElseThrow(() -> new IllegalArgumentException("Unknown value " + val));
    }

}
