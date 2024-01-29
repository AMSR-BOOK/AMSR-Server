package club.book.asmr.user.data;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Gender {
    MALE("male", "남성"),
    FEMALE("female", "여성"),
    EMPTY("empty", "비공개");

    private final String key;
    private final String title;
}
