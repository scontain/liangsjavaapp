package com.verimi.example.randomchucknorris;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RandomLogRestController {

    private final RandomLogService randomLogService;

    public RandomLogRestController(RandomLogService randomLogService) {
        this.randomLogService = randomLogService;
    }

    @GetMapping("/api/jokes/random")
    public String getJobByCategory() {
        return randomLogService.getJoke();
    }
}
