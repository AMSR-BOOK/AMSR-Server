package club.book.asmr.reading.service;

import club.book.asmr.book.entity.Book;
import club.book.asmr.book.repository.BookRepository;
import club.book.asmr.book.service.BookInfoService;
import club.book.asmr.common.exception.CustomException;
import club.book.asmr.common.exception.ErrorStatus;
import club.book.asmr.reading.data.Recommend;
import club.book.asmr.reading.data.Status;
import club.book.asmr.reading.dto.BookRecommendUpdateRequestDto;
import club.book.asmr.reading.dto.ReadingStatusUpdateRequestDto;
import club.book.asmr.reading.entity.Reading;
import club.book.asmr.reading.repository.ReadingRepository;
import club.book.asmr.user.entity.User;
import club.book.asmr.user.repository.UserRepository;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReadingService {
    private final ReadingRepository readingRepository;
    private final BookRepository bookRepository;
    private final BookInfoService bookInfoService;
    private final UserRepository userRepository;

    @Transactional
    public void updateStatus(ReadingStatusUpdateRequestDto statusUpdateRequestDto) {
        User user = userRepository.findById(statusUpdateRequestDto.userId()).orElseThrow(
                () -> new CustomException(ErrorStatus.NOT_FOUND_USER_EXCEPTION)
        );
        Book book = bookRepository.findById(statusUpdateRequestDto.isbn()).orElseThrow(
                () -> new CustomException(ErrorStatus.NOT_FOUND_BOOK_EXCEPTION)
        );
        readingRepository.findByUserAndBook(user, book).ifPresentOrElse(
                reading -> reading.updateStatus(Status.from(statusUpdateRequestDto.status())),
                () -> readingRepository.save(Reading.builder()
                        .user(user)
                        .book(book)
                        .status(Status.from(statusUpdateRequestDto.status()))
                        .build())
        );
    }

    @Transactional
    public void updateRecommend(BookRecommendUpdateRequestDto recommendUpdateRequestDto) {
        User user = userRepository.findById(recommendUpdateRequestDto.userId()).orElseThrow(
                () -> new CustomException(ErrorStatus.NOT_FOUND_USER_EXCEPTION)
        );
        Book book = bookRepository.findById(recommendUpdateRequestDto.isbn()).orElseThrow(
                () -> new CustomException(ErrorStatus.NOT_FOUND_BOOK_EXCEPTION)
        );
        readingRepository.findByUserAndBook(user, book).ifPresentOrElse(
                reading -> reading.updateRecommend(Recommend.from(recommendUpdateRequestDto.recommend())),
                () -> readingRepository.save(Reading.builder()
                        .user(user)
                        .book(book)
                        .recommend(Recommend.from(recommendUpdateRequestDto.recommend()))
                        .build())
        );
    }
}
