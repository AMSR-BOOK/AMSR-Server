package club.book.asmr.book.controller;

import club.book.asmr.book.service.BookInfoService;
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
        return ResponseEntity.ok(bookInfoService.searchBookByKeyword(keyword));
    }
}
