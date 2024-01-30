package club.book.asmr.book.service;

import club.book.asmr.book.entity.Book;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = "spring.profiles.active=dev")
class BookApiServiceTest {
    @Autowired
    private Data4LibraryOpenApiRequestService data4LibraryOpenApiRequestService;
    @Autowired
    private AladinOpenApiRequestService aladinOpenApiRequestService;

//    @Test
    void getBestSellers() {
        List<String> bestSellers = data4LibraryOpenApiRequestService.getBestSellers();
        bestSellers.forEach(System.out::println);
    }

//    @Test
    void getBookInfoByIsbn() {
        Book book = aladinOpenApiRequestService.getBookInfoByIsbn("9791158390983");
        System.out.println(book);
    }
}