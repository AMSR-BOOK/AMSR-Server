package club.book.asmr.reading.controller;

import club.book.asmr.common.exception.CustomException;
import club.book.asmr.reading.dto.BookRecommendUpdateRequestDto;
import club.book.asmr.reading.dto.ReadingStatusUpdateRequestDto;
import club.book.asmr.reading.service.ReadingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ReadingController {
    private final ReadingService readingService;

    @PostMapping("api/v1/book/status")
    public ResponseEntity<?> readingStatus(@RequestBody ReadingStatusUpdateRequestDto statusUpdateRequestDto) {
        try {
            readingService.updateStatus(statusUpdateRequestDto);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (CustomException e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
