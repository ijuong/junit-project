package com.ijuyong.junitproject.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;


@DataJpaTest // db관련 컴포넌트들만 메모리에 올라감, controller service는 안올라감
public class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    //@BeforeAll  //테스트 시작전에 한번만 실행
    @BeforeEach //각 테스트 시작전에 한번씩 실행
    public void 데이터준비(){
        String title = "junit5";
        String author = "leejuyong";

        Book book = Book.builder()
                    .title(title)
                    .author(author)
                    .build();
        bookRepository.save(book);
    }
 
    // 1. 책 등록
    @Test
    public void 책등록_test(){
        //given (데이터 준비)
        String title = "junit5";
        String author = "leejuyong";

        Book book = Book.builder()
                    .title(title)
                    .author(author)
                    .build();
        


        //when (테스트 실행)
        Book bookPS = bookRepository.save(book);

        //then (검증)
        assertEquals(title, bookPS.getTitle());
        assertEquals(author, bookPS.getAuthor());
    }


    // 2. 책 목록 보기
    @Test
    public void 책목록보기_test(){

        //given
        String title = "junit5";
        String author = "leejuyong";

        //when
        List<Book> booksPS = bookRepository.findAll();

        //then
        assertEquals(title, booksPS.get(0).getTitle());
        assertEquals(author, booksPS.get(0).getAuthor());

    }

    // 3. 책 한건 보기
    @Sql("classpath:db/tableInit.sql")
    @Test
    public void 책한건보기_test(){
        //given
        String title = "junit5";
        String author = "leejuyong";

        //when
        Book bookPS  = bookRepository.findById(1L).get();

        //then
        assertEquals(title, bookPS.getTitle());
        assertEquals(author, bookPS.getAuthor());
    }

    // 4. 책수정
    @Sql("classpath:db/tableInit.sql")
    @Test
    public void 책삭제_test(){
        //given
        Long id = 1L;

        //when
        bookRepository.deleteById(id);

        //then
        assertFalse(bookRepository.findById(id).isPresent());
    }

    // 5. 책삭제
}
