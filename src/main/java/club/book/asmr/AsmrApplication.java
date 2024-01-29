package club.book.asmr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class AsmrApplication {

    public static void main(String[] args) {
        SpringApplication.run(AsmrApplication.class, args);
    }

}
