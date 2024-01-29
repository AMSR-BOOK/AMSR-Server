package club.book.asmr.reading.data;

import java.util.Arrays;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ReportType {
    TRANSCRIPT("필사"),
    APPRECIATION("감상"),
    ;

    private final String title;

    public static ReportType from(String name) {
        return Arrays.stream(ReportType.values())
                .filter(reportType -> reportType.name().equals(name.toUpperCase()))
                .findFirst()
                .orElse(TRANSCRIPT);
    }
}
