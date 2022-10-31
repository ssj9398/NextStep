package com.example.junitproject.service;

import com.example.junitproject.domain.Book;
import com.example.junitproject.domain.BookRepository;
import com.example.junitproject.util.MailSender;
import com.example.junitproject.util.MailSenderStub;
import com.example.junitproject.web.dto.BookResponseDto;
import com.example.junitproject.web.dto.BookSaveReqDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

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
        List<BookResponseDto> dtos = bookService.책목록보기();

        //then
        assertThat(dtos.get(0).getTitle()).isEqualTo("제목1");
        assertThat(dtos.get(0).getAuthor()).isEqualTo("저자1");
        assertThat(dtos.get(1).getTitle()).isEqualTo("제목2");
        assertThat(dtos.get(1).getAuthor()).isEqualTo("저자2");
    }

}