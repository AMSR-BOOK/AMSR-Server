package club.book.asmr;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public ResponseEntity<String> hello(@RequestParam(value = "name", defaultValue = "World") String name) {
        return ResponseEntity.ok(String.format("Hello %s!", name));
    }
}
