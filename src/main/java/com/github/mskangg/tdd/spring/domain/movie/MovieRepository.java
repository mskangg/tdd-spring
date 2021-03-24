package com.github.mskangg.tdd.spring.domain.movie;

import java.util.List;

public interface MovieRepository {
    List<Movie> findByQuery(String query);
}
