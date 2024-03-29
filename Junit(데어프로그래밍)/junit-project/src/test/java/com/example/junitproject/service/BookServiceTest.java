package com.example.junitproject.service;

import com.example.junitproject.domain.Book;
import com.example.junitproject.domain.BookRepository;
import com.example.junitproject.util.MailSender;
import com.example.junitproject.web.dto.response.BookListResponseDto;
import com.example.junitproject.web.dto.response.BookResponseDto;
import com.example.junitproject.web.dto.request.BookSaveReqDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ActiveProfiles("dev")
@ExtendWith(MockitoExtension.class)
class BookServiceTest {

    @InjectMocks
    private BookService bookService;

    @Mock
    private BookRepository bookRepository;

    @Mock
    private MailSender mailSender;

    @Test
    public void 책등록하기테스트(){
        //given
        BookSaveReqDto dto = new BookSaveReqDto();
        dto.setTitle("제목1");
        dto.setAuthor("저자1");

        //stub
        when(bookRepository.save(any())).thenReturn(dto.toEntity());
        when(mailSender.send()).thenReturn(true);

        //when
        BookResponseDto bookResponseDto = bookService.책등록하기(dto);

        //then
        assertThat(bookResponseDto.getTitle()).isEqualTo(dto.getTitle());
        assertThat(bookResponseDto.getAuthor()).isEqualTo(dto.getAuthor());
    }

    @Test
    public void 책목록보기테스트(){
        //given

        //stub
        List<Book> bookList = new ArrayList<>();
        bookList.add(new Book(1L,"제목1","저자1"));
        bookList.add(new Book(2L,"제목2","저자2"));

        when(bookRepository.findAll()).thenReturn(bookList);

        //when
        BookListResponseDto bookListResponseDto = bookService.책목록보기();

        //then
        assertThat(bookListResponseDto.getItems().get(0).getTitle()).isEqualTo("제목1");
        assertThat(bookListResponseDto.getItems().get(0).getAuthor()).isEqualTo("저자1");
        assertThat(bookListResponseDto.getItems().get(1).getTitle()).isEqualTo("제목2");
        assertThat(bookListResponseDto.getItems().get(1).getAuthor()).isEqualTo("저자2");
    }

    @Test
    void 책한건보기(){
        //given
        Long id = 1L;

        //stub
        Book book = new Book(1L, "제목1","저자1");
        Optional<Book>  optBook = Optional.of(book);
        when(bookRepository.findById(id)).thenReturn(optBook);

        //when
        BookResponseDto bookResponseDto = bookService.책한건보기(id);

        //then
        assertThat(bookResponseDto.getTitle()).isEqualTo(book.getTitle());
        assertThat(bookResponseDto.getAuthor()).isEqualTo(book.getAuthor());
    }

    @Test
    void 책수정하기(){
        //given
        Long id = 1L;
        BookSaveReqDto dto = new BookSaveReqDto();
        dto.setTitle("제목2");
        dto.setAuthor("저자2");

        //stub
        Book book = new Book(1L, "제목1","저자1");
        Optional<Book>  optBook = Optional.of(book);
        when(bookRepository.findById(id)).thenReturn(optBook);

        //when
        BookResponseDto bookResponseDto = bookService.책수정하기(id, dto);

        //then
        assertThat(bookResponseDto.getAuthor()).isEqualTo(dto.getAuthor());
        assertThat(bookResponseDto.getTitle()).isEqualTo(dto.getTitle());
    }

}