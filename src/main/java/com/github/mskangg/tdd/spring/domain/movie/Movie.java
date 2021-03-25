package com.github.mskangg.tdd.spring.domain.movie;

import lombok.*;
import org.springframework.util.StringUtils;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Movie {
    private String title;
    private String link;
    private String actor;
    private Float userRating;

    public String getTitle() {
        return removeSpecialCharacter(title);
    }

    private String removeSpecialCharacter(String title) {
        title = StringUtils.replace(title, "<b>", "");
        title = StringUtils.replace(title, "</b>", "");
        return title;
    }
}
