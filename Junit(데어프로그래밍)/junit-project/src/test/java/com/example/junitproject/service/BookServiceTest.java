package com.example.junitproject.service;

import com.example.junitproject.domain.BookRepository;
import com.example.junitproject.util.MailSenderStub;
import com.example.junitproject.web.dto.BookResponseDto;
import com.example.junitproject.web.dto.BookSaveReqDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class BookServiceTest {

    @Autowired  //DI
    private BookRepository bookRepository;

    @Test
    public void 책등록하기테스트(){
        //given
        BookSaveReqDto dto = new BookSaveReqDto();
        dto.setTitle("제목1");
        dto.setAuthor("저자1");

        //stub
        MailSenderStub mailSenderStub = new MailSenderStub();

        //when
        BookService bookService = new BookService(bookRepository, mailSenderStub);
        BookResponseDto bookResponseDto = bookService.책등록하기(dto);

        //then
        assertEquals(dto.getTitle(), bookResponseDto.getTitle());
        assertEquals(dto.getAuthor(), bookResponseDto.getAuthor());
    }

}