package com.github.mskangg.tdd.spring.domain.movie;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Movie {
    private String title;
    private String link;
    private String actor;
    private Float userRating;

    @Builder
    public Movie(String title, String link, String actor, Float userRating) {
        this.title = title;
        this.link = link;
        this.actor = actor;
        this.userRating = userRating;
    }

    public String getTitle() {
        return removeSpecialCharacter(title);
    }

    private String removeSpecialCharacter(String title) {
        title = StringUtils.replace(title, "<b>", "");
        title = StringUtils.replace(title, "</b>", "");
        return title;
    }
}
