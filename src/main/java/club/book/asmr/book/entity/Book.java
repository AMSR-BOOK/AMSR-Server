package club.book.asmr.book.entity;

import club.book.asmr.common.entity.BaseTime;
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Book extends BaseTime {
    @Id
    private String isbn;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private Integer itemPage;
    @Column
    private String description;
    @Column
    private String frontCoverImageUrl;
    @Column
    private String sideCoverImageUrl;
    @Column
    private Integer sizeDepth;
    @Column
    private Integer sizeWidth;
    @Column
    private Integer sizeHeight;
    @Column
    private Timestamp publishedAt;
    @Column
    private String author;
    @Column
    private String publisher;
}
