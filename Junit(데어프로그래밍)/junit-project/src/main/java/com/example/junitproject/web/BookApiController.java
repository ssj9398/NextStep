package com.example.junitproject.web;

import com.example.junitproject.service.BookService;
import com.example.junitproject.web.dto.response.BookResponseDto;
import com.example.junitproject.web.dto.request.BookSaveReqDto;
import com.example.junitproject.web.dto.response.CMRespDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class BookApiController {

    private final BookService bookService;

    //1. 책 등록
    @PostMapping("/api/v1/book")
    public ResponseEntity<?> saveBook(@RequestBody @Valid BookSaveReqDto bookSaveReqDto, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            Map<String, String> errorMap = new HashMap<>();
            for (FieldError fe : bindingResult.getFieldErrors()) {
                errorMap.put(fe.getField(), fe.getDefaultMessage());
                System.out.println("errorMap = " + errorMap.toString());
            }
            throw new RuntimeException(errorMap.toString());
        }
        BookResponseDto responseDto = bookService.책등록하기(bookSaveReqDto);
        return new ResponseEntity<>(CMRespDto.builder().code(1).msg("글 저장 성공").body(responseDto).build(),
                HttpStatus.CREATED);
    }

    //2. 책목록보기
    public ResponseEntity<?> getBookList() {
        return null;
    }

    //3. 책한건보기
    public ResponseEntity<?> getBookOne() {
        return null;
    }

    //4. 책삭제
    public ResponseEntity<?> deleteBook() {
        return null;
    }

    //5. 책수정
    public ResponseEntity<?> updateBook() {
        return null;
    }
}
