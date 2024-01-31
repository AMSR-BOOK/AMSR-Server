package club.book.asmr.reading.service;

import club.book.asmr.reading.dto.BookRecommendUpdateRequestDto;
import club.book.asmr.reading.dto.ReadingStatusUpdateRequestDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Description;

@SpringBootTest(properties = "spring.profiles.active=dev")
class ReadingServiceTest {
    @Autowired
    private ReadingService readingService;

    @Description("도서 추천 테스트")
    @Test
    void recommendBook() {// 9788965965046: 도파미네이션
        BookRecommendUpdateRequestDto requestDto = new BookRecommendUpdateRequestDto("9788957077740", "yes");
        readingService.updateRecommend(requestDto);
    }

    @Description("독서 상태 테스트")
    @Test
    void readingStatus() {// 9788957077740: 파과
        ReadingStatusUpdateRequestDto requestDto = new ReadingStatusUpdateRequestDto("9788965965046", "reading");
        readingService.updateStatus(requestDto);
    }
}