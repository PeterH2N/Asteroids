package dk.sdu.petni23.scoresystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class ScoringApplication {
    private int score = 0;

    public static void main(String[] args) {
        SpringApplication.run(ScoringApplication.class, args);
    }

    @GetMapping("/score/add")
    public int addToScore(@RequestParam(name = "score") int score) {
        this.score += score;
        return this.score;
    }
    @GetMapping("/score/set")
    public int setScore(@RequestParam(name = "score") int score) {
        this.score = score;
        return this.score;
    }

    @GetMapping("/score")
    public int getScore() {
        return score;
    }


}
