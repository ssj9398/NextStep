package com.example.junitproject.domain;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest  //DB 와 관련된 컴포넌트만 메모리에 로딩
class BookRepositoryTest {

    @Autowired // DI
    private BookRepository bookRepository;

    //1. 책 등록
    @Test
    void 책_등록_테스트(){
        //given 데이터 준비
        String title = "title";
        String author = "author";
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

    //3. 책 한건보기

    //4. 책 수정

    //5. 책 삭제

}