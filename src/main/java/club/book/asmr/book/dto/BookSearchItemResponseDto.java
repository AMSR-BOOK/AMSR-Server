package club.book.asmr.book.dto;

import club.book.asmr.book.entity.Book;
import club.book.asmr.reading.data.Recommend;
import club.book.asmr.reading.data.Status;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class BookSearchItemResponseDto implements Serializable {
    String isbn;
    String title;
    Integer itemPage;
    String frontCoverImageUrl;
    String sideCoverImageUrl;
    String author;
    String publisher;
    Status status;
    Recommend recommend;

    public static BookSearchItemResponseDto of(Book book) {
        return BookSearchItemResponseDto.builder()
                .isbn(book.getIsbn())
                .title(book.getTitle())
                .itemPage(book.getItemPage())
                .frontCoverImageUrl(book.getFrontCoverImageUrl())
                .sideCoverImageUrl(book.getSideCoverImageUrl())
                .author(book.getAuthor())
                .publisher(book.getPublisher())
                .status(Status.NONE)
                .recommend(Recommend.EMPTY)
                .build();
    }

    public void updateStatus(Status status) {
        this.status = status;
    }

    public void updateRecommend(Recommend recommend) {
        this.recommend = recommend;
    }
}