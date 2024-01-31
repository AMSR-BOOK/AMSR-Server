package club.book.asmr.book.controller;

import club.book.asmr.book.dto.BookSearchItemResponseDto;
import club.book.asmr.book.service.BookInfoService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BookController {
    private final BookInfoService bookInfoService;

    @GetMapping("/api/v1/book")
    public ResponseEntity<?> searchBook(@RequestParam(name = "keyword") String keyword) {
        try {
            List<BookSearchItemResponseDto> responseDtos = bookInfoService.searchBookByKeyword(keyword);
            if (responseDtos.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(responseDtos);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    // TODO 도서 직접 추가 isbn13
    // TODO 도서 상세 검색
}
