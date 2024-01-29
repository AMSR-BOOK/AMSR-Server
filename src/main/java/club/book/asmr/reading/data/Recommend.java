package club.book.asmr.reading.data;

import java.util.Arrays;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Recommend {
    YES("추천"),
    NO("비추천"),
    EMPTY("추천 없음");

    private final String title;

    public static Recommend from(String name) {
        return Arrays.stream(Recommend.values())
                .filter(recommend -> recommend.name().equals(name.toUpperCase()))
                .findFirst()
                .orElse(EMPTY);
    }
}
