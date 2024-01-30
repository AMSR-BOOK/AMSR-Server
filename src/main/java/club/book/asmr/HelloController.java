package club.book.asmr;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/ping")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("pong");
    }
}
