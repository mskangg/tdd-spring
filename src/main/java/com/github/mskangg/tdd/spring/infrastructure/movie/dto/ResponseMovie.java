package com.github.mskangg.tdd.spring.infrastructure.movie.dto;

import lombok.*;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ResponseMovie {
    private long total;
    private List<item> items;

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class item {
        private String movieTitle;
        private String movieLink;
        private String movieActor;
        private Float userRating;
    }
}
