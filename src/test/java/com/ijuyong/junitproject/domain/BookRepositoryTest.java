package com.ijuyong.junitproject.domain;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest // db관련 컴포넌트들만 메모리에 올라감, controller service는 안올라감
public class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;
 
    // 1. 책 등록
    @Test
    public void 책등록_test(){
        System.out.println("책등록_test 실행");
    }


    // 2. 책 목록 보기

    // 3. 책 한건 보기

    // 4. 책수정

    // 5. 책삭제
}
