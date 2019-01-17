package com.verimi.example.randomchucknorris;

import com.fasterxml.jackson.databind.JsonNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Map;
import java.util.Random;


@SpringBootApplication
public class RandomChuckNorrisApplication {


	public static void main(String[] args) {
		SpringApplication.run(RandomChuckNorrisApplication.class, args);
	}

	@Component
	@EnableScheduling
	public static class RandomLog {
		private  static final Logger log = LoggerFactory.getLogger(RandomLog.class);

		final RestTemplate restTemplate = new RestTemplate();
		public static final String URL_PATTERN = "https://api.chucknorris.io/jokes/random?category=";

		@Scheduled(cron = "${application.random.cron}")
		public void randomChuckNorris() {
			final Random random = new Random();
			int i = random.nextInt(3);

			if (i == 0) {
				log.info(chuckSays("dev"));
			} else if (i == 1) {

				log.warn(chuckSays("career"));
			} else {
				log.error(chuckSays("political"));
			}
		}

		private String chuckSays(String category) {
			final ResponseEntity<JsonNode> map = restTemplate.exchange(URL_PATTERN + category, HttpMethod.GET, requestEntity(), JsonNode.class);
			return map.getBody().get("value").asText("Chuck Norris");
		}

		private static HttpEntity requestEntity() {
			HttpHeaders headers = new HttpHeaders();
			headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
			headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
			return new HttpEntity<>(null, headers);
		}

	}
}

