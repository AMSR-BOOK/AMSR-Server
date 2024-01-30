package club.book.asmr.book.service;

import club.book.asmr.book.dto.BookSearchItemResponseDto;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Description;

@SpringBootTest(properties = "spring.profiles.active=dev")
class BookInfoServiceTest {
    @Autowired
    private Data4LibraryOpenApiRequestService data4LibraryOpenApiRequestService;
    @Autowired
    private AladinOpenApiRequestService aladinOpenApiRequestService;
    @Autowired
    private BookInfoService bookInfoService;

    @Description("책 정보 저장 테스트")
//    @Test
    void initBookInfo() {
        data4LibraryOpenApiRequestService.getBestSellers()
                .forEach(isbn -> bookInfoService.saveBookInfo(isbn));
    }

    @Description("책 검색 테스트 : 작가")
    @Test
    void searchBookByAuthor() {
        List<BookSearchItemResponseDto> books = bookInfoService.searchBookByKeyword("정세랑");
        System.out.println("검색 결과: ");
        books.forEach(System.out::println);
    }

    @Description("책 검색 테스트 : 출판사")
    @Test
    void searchBookByPublisher() {
        List<BookSearchItemResponseDto> books = bookInfoService.searchBookByKeyword("문학동네");
        System.out.println("검색 결과: ");
        books.forEach(System.out::println);
    }

    @Description("책 검색 테스트 : 제목")
    @Test
    void searchBookByTitle() {
        List<BookSearchItemResponseDto> books = bookInfoService.searchBookByKeyword("달러구트 꿈 백화점");
        System.out.println("검색 결과: ");
        books.forEach(System.out::println);
    }
}