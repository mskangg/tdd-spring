package com.github.mskangg.tdd.spring.domain.movie;

import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

public class MovieGroup {
    private final List<Movie> movies;
    private final static int AVERAGE_USER_RATING_TOP_NUM = 2; // 상위 2개 항목

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

    public OptionalDouble calculateAverageUserRating() {
        return getMoviesOrderRating().stream()
                .limit(AVERAGE_USER_RATING_TOP_NUM)
                .mapToDouble(Movie::getUserRating)
                .average();
    }
}
