package com.verimi.example.randomchucknorris;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RandomLogRepository extends CrudRepository<RandomLog, Long> {
    Optional<RandomLog> findByJoke(String joke);
}
