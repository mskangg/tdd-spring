package com.github.mskangg.tdd.spring.domain.movie;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MovieDto {
    private String movieTitle;
    private String movieLink;
    private String movieActor;
    private Float userRating;

    @Builder
    public MovieDto(String movieTitle, String movieLink, String movieActor, Float userRating) {
        this.movieTitle = movieTitle;
        this.movieLink = movieLink;
        this.movieActor = movieActor;
        this.userRating = userRating;
    }
}
