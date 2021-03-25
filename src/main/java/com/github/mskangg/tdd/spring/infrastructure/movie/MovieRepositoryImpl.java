package com.github.mskangg.tdd.spring.infrastructure.movie;

import com.github.mskangg.tdd.spring.config.NaverProperties;
import com.github.mskangg.tdd.spring.domain.movie.Movie;
import com.github.mskangg.tdd.spring.domain.movie.MovieRepository;
import com.github.mskangg.tdd.spring.infrastructure.movie.dto.ResponseMovie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class MovieRepositoryImpl implements MovieRepository {

    private final RestTemplate restTemplate;
    private final NaverProperties naverProperties;

    @Autowired
    public MovieRepositoryImpl(RestTemplate restTemplate, NaverProperties naverProperties) {
        this.restTemplate = restTemplate;
        this.naverProperties = naverProperties;
    }

    @Override
    public List<Movie> findByQuery(String query) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("X-Naver-Client-Id", naverProperties.getClientId());
        httpHeaders.add("X-Naver-Client-Secret", naverProperties.getClientSecret());
        String url = naverProperties.getMovieUrl() + "?query=" + query;

        return restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<>(httpHeaders), ResponseMovie.class)
                .getBody()
                .getItems()
                .stream()
                .map(item -> Movie.builder()
                        .title(item.getMovieTitle())
                        .link(item.getMovieLink())
                        .actor(item.getMovieActor())
                        .userRating(item.getUserRating())
                        .build())
                .collect(Collectors.toList());
    }
}
