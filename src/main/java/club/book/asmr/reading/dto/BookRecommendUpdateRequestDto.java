package club.book.asmr.reading.dto;

public record BookRecommendUpdateRequestDto(String isbn, String recommend) {
    public Long userId() {  // TODO : jwt token에서 userId 추출하고 이거 지우기
        return 2L;
    }
}