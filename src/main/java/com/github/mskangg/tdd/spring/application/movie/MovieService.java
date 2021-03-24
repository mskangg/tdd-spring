package com.github.mskangg.tdd.spring.application.movie;

import com.github.mskangg.tdd.spring.domain.movie.Movie;
import com.github.mskangg.tdd.spring.domain.movie.MovieGroup;
import com.github.mskangg.tdd.spring.domain.movie.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

    private final MovieRepository movieRepository;

    @Autowired
    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public List<Movie> findByQuery(String query) {
        return getMovieGroup(query).getMovies();
    }

    public List<Movie> findByQueryOrderRating(String query) {
        return getMovieGroup(query).getMoviesOrderRating();
    }

    private MovieGroup getMovieGroup(String query) {
        return new MovieGroup(findByQueryImpl(query));
    }

    private List<Movie> findByQueryImpl(String query) {
        return movieRepository.findByQuery(query);
    }
}
