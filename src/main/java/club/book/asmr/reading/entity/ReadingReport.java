package club.book.asmr.reading.entity;

import club.book.asmr.common.entity.BaseTime;
import club.book.asmr.reading.data.ReportType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

@Table
@Getter
@Entity
@NoArgsConstructor
public class ReadingReport extends BaseTime {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "reading_report_id", nullable = false)
    private Long id;
    @Column
    private Integer bookmark;
    @Enumerated(EnumType.STRING)
    @ColumnDefault("'transcript'")
    private ReportType reportType;
    @Column
    private String body;
    @Column
    private String captureImageUrl;
    @ManyToOne
    private Reading reading;

    @Builder
    public ReadingReport(Integer bookmark, ReportType reportType, String body, String captureImageUrl) {
        this.bookmark = bookmark;
        this.reportType = reportType;
        this.body = body;
        this.captureImageUrl = captureImageUrl;
    }

    public void updateBookmark(Integer bookmark) {
        this.bookmark = bookmark;
    }

    public void updateReportType(ReportType reportType) {
        this.reportType = reportType;
    }

    public void updateBody(String body) {
        this.body = body;
    }

    public void updateCaptureImageUrl(String captureImageUrl) {
        this.captureImageUrl = captureImageUrl;
    }
}