package com.github.mskangg.tdd.spring.domain.movie;

import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository {
    ResponseMovie findByQuery(String query);
}
