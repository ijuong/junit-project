package com.ijuyong.junitproject.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ijuyong.junitproject.domain.Book;
import com.ijuyong.junitproject.domain.BookRepository;
import com.ijuyong.junitproject.web.dto.BookRespDto;
import com.ijuyong.junitproject.web.dto.BookSaveReqDto;
import com.util.MailSender;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BookService {

    private final BookRepository bookRepository;
    private final MailSender mailSender;

    // 1.책등록
    @Transactional(rollbackFor = RuntimeException.class)
    public BookRespDto 책등록하기(BookSaveReqDto dto) {
        Book bookPS = bookRepository.save(dto.toEntity());

        if (bookPS != null) {
            if (!mailSender.send()) {
                throw new RuntimeException("메일이 전송되지 않았습니다.");
            }
        }

        return bookPS.toDto();
    }

    // 2. 책목록보기
    public List<BookRespDto> 책목록보기() {

        List<BookRespDto> dtos = bookRepository.findAll().stream()
                //.map((bookPS)->bookPS.toDto())
                .map(Book::toDto) 
                .collect(Collectors.toList());

        dtos.stream().forEach((dto) -> {
            System.out.println("------------ real code ---------");
            System.out.println(dto.getId());
            System.out.println(dto.getTitle());

        });

        return dtos;
    }

    // 3. 책한건보기
    public BookRespDto 책한건보기(Long id) {

        Optional<Book> bookOP = bookRepository.findById(id);
        if (bookOP.isPresent()) {
            Book BookPS = bookOP.get();
            return BookPS.toDto();
        } else {
            throw new RuntimeException("id를 찾을수 없습니다");
        }
    }

    // 4. 책삭제
    @Transactional(rollbackFor = RuntimeException.class)
    public void 책삭제하기(Long id) {
        bookRepository.deleteById(id);
    }

    // 5. 책수정
    @Transactional(rollbackFor = RuntimeException.class)
    public BookRespDto 책수정하기(Long id, BookSaveReqDto dto) {
        Optional<Book> bookOP = bookRepository.findById(id);
        if (bookOP.isPresent()) {
            Book bookPS = bookOP.get();
            bookPS.update(dto.getTitle(), dto.getAuthor());
            return bookPS.toDto();
        } else {
            throw new RuntimeException("id를 찾을수 없습니다");
        }

    }

}
