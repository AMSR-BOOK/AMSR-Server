package club.book.asmr.book.service;

import club.book.asmr.book.dto.BookSearchItemResponseDto;
import club.book.asmr.book.entity.Book;
import club.book.asmr.book.repository.BookRepository;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookInfoService {
    private final BookRepository bookRepository;
    private final AladinOpenApiRequestService aladinOpenApiRequestService;

    @Transactional
    public void saveBookInfo(String isbn) {
        bookRepository.findById(isbn).orElseGet(() -> {
            log.info("isbn: {}", isbn);
            try {
                Book book = aladinOpenApiRequestService.getBookInfoByIsbn(isbn);
                return bookRepository.save(book);
            } catch (Exception e) {
                log.error("Error while saving book info: {}", e.getMessage(), e);
                return null;
            }
        });
    }

    public List<BookSearchItemResponseDto> searchBookByKeyword(String keyword) {
        Set<BookSearchItemResponseDto> bookSearchItemResponseDtos = new HashSet<>();
        try {
            Long.parseLong(keyword);
            log.info("keyword is isbn");
            bookRepository.findById(keyword)
                    .ifPresent(book -> bookSearchItemResponseDtos.add(BookSearchItemResponseDto.of(book)));
        } catch (NumberFormatException e) {
            List<Book> allByTitleContains = bookRepository.findAllByTitleContains(keyword);
            allByTitleContains.stream().map(BookSearchItemResponseDto::of).forEach(bookSearchItemResponseDtos::add);
            List<Book> allByAuthorContains = bookRepository.findAllByAuthorContains(keyword);
            allByAuthorContains.stream().map(BookSearchItemResponseDto::of).forEach(bookSearchItemResponseDtos::add);
            List<Book> allByPublisherContains = bookRepository.findAllByPublisherContains(keyword);
            allByPublisherContains.stream().map(BookSearchItemResponseDto::of).forEach(bookSearchItemResponseDtos::add);
        }

        return new ArrayList<>(bookSearchItemResponseDtos);
    }
}
