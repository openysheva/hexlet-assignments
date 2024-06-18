package exercise.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

// BEGIN
import org.springframework.beans.factory.annotation.Autowired;
import exercise.daytime.Daytime;

@RestController
public class WelcomeController {
    @Autowired
    private Daytime daytime;

    @GetMapping(path = "/welcome")
    public String welcome() {
        var name = daytime.getName();
        return "It is " + name + " now! Welcome to Spring!";
    }
}
// END
