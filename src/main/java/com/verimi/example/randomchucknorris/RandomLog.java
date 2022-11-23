package com.verimi.example.randomchucknorris;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(indexes = {
        @Index(name = "idx_category", columnList = "category"),
        @Index(name = "idx_joke", columnList = "joke", unique = true)
})
public class RandomLog {

    public RandomLog() {
    }

    public RandomLog(String category, String joke) {
        this.category = category;
        this.joke = joke;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String category;
    private String joke;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getJoke() {
        return joke;
    }

    public void setJoke(String joke) {
        this.joke = joke;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RandomLog randomLog = (RandomLog) o;
        return Objects.equals(id, randomLog.id) && Objects.equals(category, randomLog.category) && Objects.equals(joke, randomLog.joke);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, category, joke);
    }
}
