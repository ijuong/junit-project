package com.ijuyong.junitproject.web.dto;

import com.ijuyong.junitproject.domain.Book;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter // controller에서 setter가 호출되면서 dto에 값이 채워짐
public class BookSaveReqDto {
    private String title;
    private String author;

    public Book toEntity(){
        return Book.builder()
            .title(title)
            .author(author)
            .build();
    }
}
