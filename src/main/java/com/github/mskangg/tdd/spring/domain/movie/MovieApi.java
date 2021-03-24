package com.github.mskangg.tdd.spring.domain.movie;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MovieApi {
    private long total;
    private List<MovieDto> items;

    @Builder
    public MovieApi(long total, List<MovieDto> items) {
        this.total = total;
        this.items = items;
    }
}
