package com.example.junitproject.service;

import com.example.junitproject.domain.Book;
import com.example.junitproject.domain.BookRepository;
import com.example.junitproject.web.dto.BookResponseDto;
import com.example.junitproject.web.dto.BookSaveReqDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;

    //1. 책 등록
    @Transactional(rollbackFor = RuntimeException.class)
    public BookResponseDto 책등록하기(BookSaveReqDto dto){
        Book bookEntity = bookRepository.save(dto.toEntity());
        return new BookResponseDto().toDto(bookEntity);
    }

    //2. 책목록보기
    public List<BookResponseDto> 책목록보기(){
        return bookRepository.findAll().stream()
                .map(new BookResponseDto()::toDto)
                .collect(Collectors.toList());
    }

    //3. 책한건보기
    public BookResponseDto 책한건보기(Long id){
        Optional<Book> bookEntity = bookRepository.findById(id);
        if(!bookEntity.isEmpty()){
            return new BookResponseDto().toDto(bookEntity.get());
        }else {
            throw new RuntimeException("해당 아이디를 찾을 수 없습니다.");
        }
    }

    //4. 책삭제

    //5. 책수정
}
