package com.ijuyong.junitproject.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class BookRespDto {
    private long id;
    private String title;
    private String author;

    @Builder
    public BookRespDto(long id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
    }
}
