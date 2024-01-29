package club.book.asmr.reading.repository;

import club.book.asmr.reading.entity.ReadingTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReadingTimeRepository extends JpaRepository<ReadingTime, Long> {
    List<ReadingTime> findAllByReadingId(Long readingId);
}