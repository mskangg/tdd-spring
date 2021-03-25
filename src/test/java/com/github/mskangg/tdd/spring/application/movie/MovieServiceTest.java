package com.github.mskangg.tdd.spring.application.movie;

import com.github.mskangg.tdd.spring.domain.movie.Movie;
import com.github.mskangg.tdd.spring.domain.movie.MovieRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.List;

@ExtendWith(SpringExtension.class)
class MovieServiceTest {

    @Mock
    private MovieRepository movieRepository;

    @InjectMocks
    private MovieService movieService;

    private List<Movie> getStubMovies() {
        return Arrays.asList(
                (Movie.builder().title("<b>영화1</b>").actor("배우1").userRating(9.3f).build()),
                (Movie.builder().title("<b>영화2</b>").actor("배우2").userRating(9.2f).build()),
                (Movie.builder().title("<b>영화3</b>").actor("배우3").userRating(9.1f).build()),
                (Movie.builder().title("<b>영화4</b>").actor("배우4").userRating(9.6f).build()),
                (Movie.builder().title("<b>영화5</b>").actor("배우5").userRating(9.4f).build()),
                (Movie.builder().title("<b>영화6</b>").actor("배우6").userRating(0.0f).build()),
                (Movie.builder().title("<b>영화7</b>").actor("배우7").userRating(null).build())
        );
    }

    @Test
    @DisplayName("사용자 평점 순으로 정렬이 잘 되는지")
    void arranged_well_in_user_ratings() {

        //given
        float expectedUserRanking = 9.6f;
        Mockito.when(movieRepository.findByQuery(Mockito.any())).thenReturn(getStubMovies());

        //when
        List<Movie> sortedMovies = movieService.findByQueryOrderRating(Mockito.any());

        //then
        Assertions.assertEquals(expectedUserRanking, sortedMovies.get(0).getUserRating());
    }

    @Test
    @DisplayName("평점이 0과 null인 데이터는 제외하는지")
    void user_ratings_exclude_zero_or_null() {

        //given
        float expectedUserRanking = 9.6f;
        int expectedSize = 5;
        Mockito.when(movieRepository.findByQuery(Mockito.any())).thenReturn(getStubMovies());

        //when
        List<Movie> sortedMovies = movieService.findByQueryOrderRating(Mockito.any());

        //then
        Assertions.assertEquals(expectedUserRanking, sortedMovies.get(0).getUserRating());
        Assertions.assertEquals(expectedSize, sortedMovies.size());
    }

    @Test
    @DisplayName("<b>, </b> 제거 잘 하는지")
    void remove_special_characters_when_mapping_title() {

        //given
        int expectedSpecialCharacterCount = 0;
        Mockito.when(movieRepository.findByQuery(Mockito.any())).thenReturn(getStubMovies());

        //when
        List<Movie> foundMovies = movieService.findByQuery(Mockito.any());

        //then
        Assertions.assertEquals(expectedSpecialCharacterCount,
                StringUtils.countOccurrencesOf(foundMovies.stream().findFirst().get().getTitle(), "<b>"));
        Assertions.assertEquals(expectedSpecialCharacterCount,
                StringUtils.countOccurrencesOf(foundMovies.stream().findFirst().get().getTitle(), "</b>"));
    }

    @Test
    @DisplayName("상위 평점 2개의 평균 값 구하기")
    void test_calculateAverageUserRating() {

        //given
        double expectedAverage = 9.5;
        Mockito.when(movieRepository.findByQuery(Mockito.any())).thenReturn(getStubMovies());

        //when
        double actualAverage = movieService.calculateAverageUserRating(Mockito.any());

        //then
        Assertions.assertEquals(expectedAverage, actualAverage);
    }

}