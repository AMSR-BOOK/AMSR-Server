package club.book.asmr;

import club.book.asmr.book.service.AladinOpenApiRequestService;
import club.book.asmr.book.service.BookInfoService;
import club.book.asmr.book.service.Data4LibraryOpenApiRequestService;
import club.book.asmr.reading.dto.BookRecommendUpdateRequestDto;
import club.book.asmr.reading.dto.ReadingStatusUpdateRequestDto;
import club.book.asmr.user.data.AuthProvider;
import club.book.asmr.user.data.Role;
import club.book.asmr.user.entity.User;
import club.book.asmr.user.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Description;

@SpringBootTest(properties = "spring.profiles.active=dev")
class DevDataInitTest {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private Data4LibraryOpenApiRequestService data4LibraryOpenApiRequestService;
    @Autowired
    private AladinOpenApiRequestService aladinOpenApiRequestService;
    @Autowired
    private BookInfoService bookInfoService;

    @Description("관리자 계정 저장 테스트")
//    @Test
    void saveAdminUser() {
        userRepository.save(
                User.builder()
                        .email("dev.devin.kr@gmail.com")
                        .nickname("devin")
                        .profileImageUrl("https://avatars.githubusercontent.com/u/50111143?v=4")
                        .externalId("50111143")
                        .role(Role.ADMIN)
                        .authProvider(AuthProvider.EMPTY)
                        .build()
        );
    }

    @Description("책 정보 저장 테스트")
//    @Test
    void initBookInfo() {
        data4LibraryOpenApiRequestService.getBestSellers()
                .forEach(isbn -> bookInfoService.saveBookInfo(isbn));
    }
}
