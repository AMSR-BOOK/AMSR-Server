package club.book.asmr.book.repository;

import club.book.asmr.book.entity.Book;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, String> {
    List<Book> findAllByTitle(String title);
    List<Book> findAllByAuthorContains(String keyword);
    List<Book> findAllByPublisherContains(String keyword);
}
