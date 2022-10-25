package com.example.junitproject.domain;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest  //DB 와 관련된 컴포넌트만 메모리에 로딩
class BookRepositoryTest {

    @Autowired // DI
    private BookRepository bookRepository;
    //@BeforeAll // 테스트 시작전에 한번만 실행
    @BeforeEach //테스트 시작전 한번씩 실행
    public void 데이터준비(){
        String title = "title";
        String author = "author";
        Book book = Book.builder()
                .title(title)
                .author(author)
                .build();
        bookRepository.save(book);
    }

    //1. 책 등록
    @Test
    void 책_등록_테스트(){
        //given 데이터 준비
        String title = "title2";
        String author = "author2";
        Book book = Book.builder()
                .title(title)
                .author(author)
                .build();

        //when 테스트 설정
        Book bookPs = bookRepository.save(book);

        //then 검증
        assertEquals(title, bookPs.getTitle());
        assertEquals(author, bookPs.getAuthor());
    }

    //2. 책 목록보기
    @Test
    void 책목록보기(){
        //given
        String title = "title";
        String author = "author";

        //when
        List<Book> bookList = bookRepository.findAll();

        //then
        System.out.println("size = " + bookList.size());
        assertEquals("title", bookList.get(0).getTitle());
        assertEquals(title, bookList.get(0).getTitle());
        assertEquals(author, bookList.get(0).getAuthor());
    }

    //3. 책 한건보기
    @Test
    void 책한건보기() {
        //given
        String title = "title";
        String author = "author";

        //when
        Book bookPs = bookRepository.findById(1L).get();

        //then
        assertEquals(title,bookPs.getTitle());
        assertEquals(author, bookPs.getAuthor());

    }
    //4. 책 수정

    //5. 책 삭제

}