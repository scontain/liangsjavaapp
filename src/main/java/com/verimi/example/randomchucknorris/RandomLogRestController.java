package com.verimi.example.randomchucknorris;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RandomLogRestController {

    private static final Logger log = LoggerFactory.getLogger(RandomLogRestController.class);
    private final RandomLogService randomLogService;

    public RandomLogRestController(RandomLogService randomLogService) {
        this.randomLogService = randomLogService;
    }

    @GetMapping("/api/jokes/random")
    public String getJokeByCategory() {
        final String joke = randomLogService.getJoke();
        log.error("Chuck is joking: " + joke);
        return joke;
    }

    @GetMapping("/api/gossips/random")
    public String getGossipByCategory() {
        final String gossip = randomLogService.getGossip();
        log.error("Chuck is gossiping: " + gossip);
        return gossip;
    }
}
