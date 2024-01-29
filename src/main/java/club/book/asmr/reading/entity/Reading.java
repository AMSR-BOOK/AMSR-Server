package club.book.asmr.reading.entity;

import club.book.asmr.book.entity.Book;
import club.book.asmr.common.entity.BaseTime;
import club.book.asmr.reading.data.Recommend;
import club.book.asmr.reading.data.Star;
import club.book.asmr.reading.data.Status;
import club.book.asmr.user.entity.User;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Getter
@NoArgsConstructor
public class Reading extends BaseTime {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "reading_id", nullable = false)
    private Long id;
    @Enumerated(EnumType.STRING)
    @Column
    private Status status;
    @Column
    private String review;
    @Enumerated(EnumType.STRING)
    @ColumnDefault("'empty'")
    private Star star;
    @Enumerated(EnumType.STRING)
    @ColumnDefault("'empty'")
    private Recommend recommend;
    @Column
    private Integer totalReadingTime;
    @Column
    private Integer numberOfPagesRead;
    @ManyToOne
    @JoinColumn(name = "book_isbn")
    private Book book;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Builder
    public Reading(Status status) {
        this.status = status;
    }

    public void updateStatus(Status status) {
        this.status = status;
    }

    public void updateReview(String review) {
        this.review = review;
    }

    public void updateStar(Star star) {
        this.star = star;
    }

    public void updateRecommend(Recommend recommend) {
        this.recommend = recommend;
    }

    public void updateNumberOfPagesRead(Integer pages) {
        this.numberOfPagesRead += pages;
    }
}
