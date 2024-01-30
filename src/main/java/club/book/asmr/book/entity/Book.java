package club.book.asmr.book.entity;

import club.book.asmr.common.entity.BaseTime;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table
public class Book extends BaseTime {
    @Id
    private String isbn;
    @Column(nullable = false, length = 200)
    private String title;
    @Column(nullable = false)
    private Integer itemPage;
    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;
    @Column(nullable = false, length = 100)
    private String frontCoverImageUrl;
    @Column(length = 100)
    private String sideCoverImageUrl;
    @Column(nullable = false)
    private Integer sizeDepth;
    @Column(nullable = false)
    private Integer sizeWidth;
    @Column(nullable = false)
    private Integer sizeHeight;
    @Column(nullable = false)
    private LocalDate publishedDate;
    @Column(nullable = false, length = 200)
    private String author;
    @Column(nullable = false)
    private String publisher;
}
