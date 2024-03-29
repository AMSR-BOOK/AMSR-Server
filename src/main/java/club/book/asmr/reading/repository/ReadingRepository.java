package club.book.asmr.reading.repository;

import club.book.asmr.reading.entity.Reading;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReadingRepository extends JpaRepository<Reading, Long> {
    List<Reading> findAllByUserId(String userId);
}
