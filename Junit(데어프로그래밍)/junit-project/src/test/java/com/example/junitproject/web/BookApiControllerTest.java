package com.example.junitproject.web;

import static org.assertj.core.api.Assertions.*;

import com.example.junitproject.domain.Book;
import com.example.junitproject.domain.BookRepository;
import com.example.junitproject.service.BookService;
import com.example.junitproject.web.dto.request.BookSaveReqDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.jdbc.Sql;

//통합 테스트
// 컨트롤러만 테스트하는게 아님
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BookApiControllerTest {

    @Autowired
    private TestRestTemplate rt;

    @Autowired
    private BookRepository bookRepository;

    private static ObjectMapper objectMapper;
    private static HttpHeaders headers;

    @BeforeAll
    public static void init(){
        objectMapper = new ObjectMapper();
        headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
    }

    @BeforeEach //테스트 시작전 한번씩 실행
    public void 데이터준비(){
        String title = "제목1";
        String author = "저자1";
        Book book = Book.builder()
                .title(title)
                .author(author)
                .build();
        bookRepository.save(book);
    }

    @Sql("classpath:db/tableInit.sql")
    @Test
    public void deleteBook_test(){
        //given
        Long id = 1L;

        //when
        HttpEntity<String> request = new HttpEntity<>(null, headers);

        ResponseEntity<String> response = rt.exchange("/api/v1/book/" + id, HttpMethod.DELETE, request, String.class);

        //then
        DocumentContext dc = JsonPath.parse(response.getBody());
        Integer code = dc.read("$.code");

        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(code).isEqualTo(1);
    }

    @Sql("classpath:db/tableInit.sql")
    @Test
    public void getBookOne(){
        //given
        Long id = 1L;

        //when
        HttpEntity<String> request = new HttpEntity<>(null, headers);

        ResponseEntity<String> response = rt.exchange("/api/v1/book/"+id, HttpMethod.GET, request, String.class);

        //then
        DocumentContext dc = JsonPath.parse(response.getBody());
        Integer code = dc.read("$.code");
        String title = dc.read("$.body.title");

        assertThat(code).isEqualTo(1);
        assertThat(title).isEqualTo("제목1");
    }

    @Sql("classpath:db/tableInit.sql")
    @Test
    public void getBookList_test(){
        //given

        //when
        HttpEntity<String> request = new HttpEntity<>(null, headers);

        ResponseEntity<String> response = rt.exchange("/api/v1/book", HttpMethod.GET, request, String.class);

        //then
        DocumentContext dc = JsonPath.parse(response.getBody());
        Integer code = dc.read("$.code");
        String title = dc.read("$.body.items[0].title");

        assertThat(code).isEqualTo(1);
        assertThat(title).isEqualTo("제목1");

    }

    @Test
    public void saveBook_test() throws JsonProcessingException {
        //given
        BookSaveReqDto bookSaveReqDto = new BookSaveReqDto();
        bookSaveReqDto.setTitle("제목1");
        bookSaveReqDto.setAuthor("저자1");

        String body = objectMapper.writeValueAsString(bookSaveReqDto);

        //when
        HttpEntity<String> request = new HttpEntity<>(body, headers);

        ResponseEntity<String> response = rt.exchange("/api/v1/book", HttpMethod.POST, request, String.class);

        //then
        DocumentContext dc = JsonPath.parse(response.getBody());
        String title= dc.read("$.body.title");
        String author = dc.read("$.body.author");

        assertThat(title).isEqualTo("제목1");
        assertThat(author).isEqualTo("저자1");
    }
}