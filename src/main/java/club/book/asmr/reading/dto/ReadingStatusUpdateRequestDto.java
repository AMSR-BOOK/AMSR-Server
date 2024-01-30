package club.book.asmr.reading.dto;

import java.io.Serializable;

public record ReadingStatusUpdateRequestDto(String isbn, String status) implements Serializable {
    public Long userId() {  // TODO : jwt token에서 userId 추출하고 이거 지우기
        return 2L;
    }
}