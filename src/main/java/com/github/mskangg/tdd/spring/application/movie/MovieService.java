package com.github.mskangg.tdd.spring.application.movie;

import com.github.mskangg.tdd.spring.domain.movie.Movie;
import com.github.mskangg.tdd.spring.domain.movie.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieService {

    private final MovieRepository movieRepository;

    @Autowired
    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public List<Movie> findByQuery(String query) {
        return findByQueryImpl(query);
    }

    public List<Movie> findByQueryOrderRating(String query) {
        return findByQueryImpl(query).stream()
                .sorted((a, b) -> b.getUserRating() > a.getUserRating() ? 1 : -1)
                .collect(Collectors.toList());
    }

    // 오픈 API 에서 오는 정보 -> 도메인으로 변경 (비즈니스)
    private List<Movie> findByQueryImpl(String query) {
        return movieRepository.findByQuery(query).getItems().stream()
                .map(movieDto -> Movie.builder()
                        .title(movieDto.getMovieTitle())
                        .link(movieDto.getMovieLink())
                        .actor(movieDto.getMovieActor())
                        .userRating(movieDto.getUserRating())
                        .build())
                .collect(Collectors.toList());
    }
}
