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
        List<Book> allByTitleContains = bookRepository.findAllByTitleContains(keyword);
        List<Book> allByAuthorContains = bookRepository.findAllByAuthorContains(keyword);
        List<Book> allByPublisherContains = bookRepository.findAllByPublisherContains(keyword);

        Set<BookSearchItemResponseDto> bookSearchItemResponseDtos = new HashSet<>();
        for (Book book : allByTitleContains) {
            bookSearchItemResponseDtos.add(BookSearchItemResponseDto.of(book));
        }
        for (Book book : allByAuthorContains) {
            bookSearchItemResponseDtos.add(BookSearchItemResponseDto.of(book));
        }
        for (Book book : allByPublisherContains) {
            bookSearchItemResponseDtos.add(BookSearchItemResponseDto.of(book));
        }

        return new ArrayList<>(bookSearchItemResponseDtos);
    }
}
