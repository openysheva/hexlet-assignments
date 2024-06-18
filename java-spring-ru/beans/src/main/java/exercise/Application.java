package exercise;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;

import exercise.daytime.Daytime;
import exercise.daytime.Day;
import exercise.daytime.Night;

// BEGIN
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.context.annotation.Bean;
// END

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    // BEGIN
    @Bean
    @RequestScope
    public Daytime getInfo() { // Имя метода не важно
        LocalDateTime now = LocalDateTime.now();
        int hour = now.getHour();
        return hour > 6 & hour < 22 ? new Day() : new Night();
    }
    // END
}
