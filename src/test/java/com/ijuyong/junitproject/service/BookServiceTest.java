package com.ijuyong.junitproject.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.ijuyong.junitproject.domain.Book;
import com.ijuyong.junitproject.domain.BookRepository;
import com.ijuyong.junitproject.web.dto.BookRespDto;
import com.ijuyong.junitproject.web.dto.BookSaveReqDto;
import com.util.MailSender;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

    @InjectMocks
    private BookService bookService;

    @Mock
    private BookRepository bookRepository;

    @Mock
    private MailSender mailSender;

    @Test
    public void 책등록하기_테스트() {

        // given
        BookSaveReqDto dto = new BookSaveReqDto();
        dto.setTitle("junit");
        dto.setAuthor("이주용");

        // stub (행동정의, 가설)
        when(bookRepository.save(any())).thenReturn(dto.toEntity());
        when(mailSender.send()).thenReturn(true);

        // when
        BookRespDto bookRespDto = bookService.책등록하기(dto);

        // then
        // assertEquals(dto.getTitle(), bookRespDto.getTitle());
        // assertEquals(dto.getAuthor(), bookRespDto.getAuthor());
        assertThat(bookRespDto.getTitle()).isEqualTo(dto.getTitle());
        assertThat(bookRespDto.getAuthor()).isEqualTo(dto.getAuthor());

    }

    @Test
    public void 책목록보기_테스트() {
        // given

        // stub
        List<Book> books = new ArrayList<>();
        books.add(new Book(1L, "junit", "juyong"));
        books.add(new Book(2L, "junit5", "ijuong"));

        when(bookRepository.findAll()).thenReturn(books);

        // when
        List<BookRespDto> bookrespDtoList = bookService.책목록보기();

        bookrespDtoList.stream().forEach((dto) -> {
            System.out.println("------------ test code ---------");
            System.out.println(dto.getId());
            System.out.println(dto.getTitle());

        });

        // then
        assertThat(bookrespDtoList.get(0).getTitle()).isEqualTo("junit");
        assertThat(bookrespDtoList.get(0).getAuthor()).isEqualTo("juyong");
        assertThat(bookrespDtoList.get(1).getTitle()).isEqualTo("junit5");
        assertThat(bookrespDtoList.get(1).getAuthor()).isEqualTo("ijuong");
    }

    @Test
    public void 책한건보기_테스트(){
        //given
        Long id = 1L;
        
        //stub
        Book book = new Book(1L, "junit", "juyong");
        Optional<Book> bookOP = Optional.of(book);
        when(bookRepository.findById(id)).thenReturn(bookOP); 
        
        //when
        BookRespDto bookRespDto = bookService.책한건보기(id);

        //then
        assertThat(bookRespDto.getTitle()).isEqualTo(book.getTitle());
        assertThat(bookRespDto.getAuthor()).isEqualTo(book.getAuthor());
        

    }

    @Test
    public void 책삭제하기_테스트(){
        //given
        Long id = 1L;
        BookSaveReqDto dto = new BookSaveReqDto();
        dto.setTitle("junit5");
        dto.setAuthor("ijuong");

        //stub
        Book book = new Book(1L, "junit", "juyong");
        Optional<Book> bookOP = Optional.of(book);
        when(bookRepository.findById(id)).thenReturn(bookOP); 

        //when
        BookRespDto bookRespDto = bookService.책수정하기(id, dto);

        //then
        assertThat(bookRespDto.getTitle()).isEqualTo(dto.getTitle());
        assertThat(bookRespDto.getAuthor()).isEqualTo(dto.getAuthor());
    }
}
