package club.book.asmr.reading.repository;

import club.book.asmr.reading.entity.ReadingReport;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReadingReportRepository extends JpaRepository<ReadingReport, Long> {
    List<ReadingReport> findAllByReadingId(Long readingId);
}