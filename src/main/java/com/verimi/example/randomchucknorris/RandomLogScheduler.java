package com.verimi.example.randomchucknorris;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class RandomLogScheduler {
    private static final Logger log = LoggerFactory.getLogger(RandomLogScheduler.class);

    private final RandomLogService randomLogService;

    @Value("${application.jokescontainer.scheduler.enabled:true}")
    private boolean enabled;

    public RandomLogScheduler(RandomLogService randomLogService) {
        this.randomLogService = randomLogService;
    }

    @Scheduled(cron = "${application.random.cron}")
    public void randomChuckNorrisJoke() {
        if (enabled) {
            log.error("chuck is joking: " + randomLogService.getJoke());
        }
    }

    @Scheduled(cron = "${application.random.gossip.cron}")
    public void randomChuckNorrisGossip() {
        if (enabled) {
            log.error("chuck is gossiping: " + randomLogService.getGossip());
        }
    }
}
