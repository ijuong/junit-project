package com.ijuyong.junitproject.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.ijuyong.junitproject.domain.BookRepository;
import com.ijuyong.junitproject.web.dto.BookRespDto;
import com.ijuyong.junitproject.web.dto.BookSaveReqDto;
import com.util.MailSenderStub;

@DataJpaTest
public class BookServiceTest {
    
    @Autowired
    private BookRepository bookRepository;
    
    @Test
    public void 책등록하기_테스트(){

        //given
        BookSaveReqDto dto = new BookSaveReqDto();
        dto.setTitle("junit");
        dto.setAuthor("이주용");

        //stub
        MailSenderStub mailSenderStub = new MailSenderStub();
        
        //when
        BookService bookService = new BookService(bookRepository, mailSenderStub);
        BookRespDto bookRespDto = bookService.책등록하기(dto);

        //then
        assertEquals(dto.getTitle(), bookRespDto.getTitle());
        assertEquals(dto.getAuthor(), bookRespDto.getAuthor());
    }
}
