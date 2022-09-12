package com.ijuyong.junitproject.web.dto;

import com.ijuyong.junitproject.domain.Book;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class BookRespDto {
    private long id;
    private String title;
    private String author;

    public BookRespDto toDto(Book bookPS){
        this.id = bookPS.getId();
        this.title = bookPS.getTitle();
        this.author = bookPS.getAuthor();
        return this;
    }
}
