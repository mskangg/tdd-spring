package com.github.mskangg.tdd.spring.domain.movie;

import java.util.List;
import java.util.stream.Collectors;

public class MovieGroup {
    private final List<Movie> movies;

    public MovieGroup(List<Movie> movies) {
        this.movies = movies;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public List<Movie> getMoviesOrderRating() {
        return movies.stream()
                .filter(b -> b.getUserRating() != null)
                .filter(b -> !(b.getUserRating()).equals(0.0f))
                .sorted((a, b) -> b.getUserRating() > a.getUserRating() ? 1 : -1)
                .collect(Collectors.toList());
    }
}
