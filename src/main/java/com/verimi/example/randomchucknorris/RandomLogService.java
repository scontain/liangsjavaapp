package com.verimi.example.randomchucknorris;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Optional;
import java.util.Random;

@Service
public class RandomLogService {

    private final RandomLogRepository randomLogRepository;

    final RestTemplate restTemplate = new RestTemplate();
    final Random random = new Random();

    public static final String URL_PATTERN = "https://api.chucknorris.io/jokes/random?category=";

    public RandomLogService(RandomLogRepository randomLogRepository) {
        this.randomLogRepository = randomLogRepository;
    }

    public String getJoke() {
        int i = random.nextInt(3);

        if (i == 0) {
            return chuckSays("dev");
        } else if (i == 1) {
            return chuckSays("career");
        } else {
            return chuckSays("dev");
        }
    }

    private String chuckSays(String category) {
        final ResponseEntity<JsonNode> map = restTemplate.exchange(URL_PATTERN + category, HttpMethod.GET, requestEntity(), JsonNode.class);
        final String joke = map.getBody().get("value").asText("Chuck Norris");

        final Optional<RandomLog> randomLogOptional = randomLogRepository.findByJoke(joke);

        if (randomLogOptional.isPresent()) {
            return randomLogOptional.get().getJoke();
        } else {
            randomLogRepository.save(new RandomLog(category, joke));
            return joke;
        }
    }

    private static HttpEntity requestEntity() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
        return new HttpEntity<>(null, headers);
    }
}
