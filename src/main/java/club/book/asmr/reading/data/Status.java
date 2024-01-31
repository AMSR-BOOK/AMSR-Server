package club.book.asmr.reading.data;

import java.util.Arrays;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Status {
    WISH("읽고 싶은"),
    READING("읽고 있는"),
    DONE("다 읽은"),
    PAUSED("나중에 읽을"),
    STOPPED("그만 읽을"),
    NONE("상태 없음");

    private final String title;

    public static Status from(String name) {
        return Arrays.stream(Status.values())
                .filter(status -> status.name().equals(name.toUpperCase()))
                .findFirst()
                .orElse(NONE);
    }
}
