package club.book.asmr.reading.data;

import java.util.Arrays;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Star {
    POINTFIVE(0.5),
    ONE(1.0),
    ONEPOINTFIVE(1.5),
    TWO(2.0),
    TWOPOINTFIVE(2.5),
    THREE(3.0),
    THREEPOINTFIVE(3.5),
    FOUR(4.0),
    FOURPOINTFIVE(4.5),
    FIVE(5.0),
    EMPTY(0.0);

    private final Double point;

    public static Star from(Double point) {
        return Arrays.stream(Star.values())
                .filter(star -> star.getPoint().equals(point))
                .findFirst()
                .orElse(EMPTY);
    }
}
