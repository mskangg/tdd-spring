package com.github.mskangg.tdd.spring.application.movie;

import com.github.mskangg.tdd.spring.domain.movie.Movie;
import com.github.mskangg.tdd.spring.domain.movie.MovieDto;
import com.github.mskangg.tdd.spring.domain.movie.MovieRepository;
import com.github.mskangg.tdd.spring.domain.movie.ResponseMovie;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(SpringExtension.class)
class MovieServiceTest {

    @Mock
    private MovieRepository movieRepository;

    @InjectMocks
    private MovieService movieService;

    @Test
    @DisplayName("사용자 평점 순으로 정렬이 잘 되는지?")
    void arranged_well_in_user_ratings() {

        //given
        float expectedUserRanking = 9.5f;
        List<MovieDto> movies = new ArrayList<>();
        movies.add(MovieDto.builder().movieTitle("영화1").movieActor("배우1").userRating(9.3f).build());
        movies.add(MovieDto.builder().movieTitle("영화2").movieActor("배우2").userRating(9.2f).build());
        movies.add(MovieDto.builder().movieTitle("영화3").movieActor("배우3").userRating(9.1f).build());
        movies.add(MovieDto.builder().movieTitle("영화4").movieActor("배우4").userRating(9.5f).build());
        movies.add(MovieDto.builder().movieTitle("영화5").movieActor("배우5").userRating(9.4f).build());
        Mockito.when(movieRepository.findByQuery("test"))
                .thenReturn(ResponseMovie.builder().items(movies).build());

        //when
        List<Movie> sortedMovies = movieService.findByQueryOrderRating("test");

        //then
        Assertions.assertEquals(expectedUserRanking, sortedMovies.get(0).getUserRating());
    }
}