package com.example.junitproject.service;

import com.example.junitproject.domain.Book;
import com.example.junitproject.domain.BookRepository;
import com.example.junitproject.util.MailSender;
import com.example.junitproject.web.dto.response.BookListResponseDto;
import com.example.junitproject.web.dto.response.BookResponseDto;
import com.example.junitproject.web.dto.request.BookSaveReqDto;
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
    private final MailSender mailSender;

    //1. 책 등록
    @Transactional(rollbackFor = RuntimeException.class)
    public BookResponseDto 책등록하기(BookSaveReqDto dto){
        Book bookEntity = bookRepository.save(dto.toEntity());
        if(bookEntity!=null){
            if(!mailSender.send()){
                throw new RuntimeException("메일이 전송되지 않았습니다.");
            }
        }
        return bookEntity.toDto();
    }

    //2. 책목록보기
    public BookListResponseDto 책목록보기(){
        List<BookResponseDto> dtos = bookRepository.findAll().stream()
                .map(Book::toDto)
                .collect(Collectors.toList());

        return BookListResponseDto.builder().bookList(dtos).build();
    }

    //3. 책한건보기
    public BookResponseDto 책한건보기(Long id){
        Optional<Book> bookEntity = bookRepository.findById(id);
        if(!bookEntity.isEmpty()){
            return bookEntity.get().toDto();
        }else {
            throw new RuntimeException("해당 아이디를 찾을 수 없습니다.");
        }
    }

    //4. 책삭제
    @Transactional(rollbackFor = RuntimeException.class)
    public void 책삭제하기(Long id){
        bookRepository.deleteById(id);
    }

    //5. 책수정
    @Transactional(rollbackFor = RuntimeException.class)
    public BookResponseDto 책수정하기(Long id, BookSaveReqDto dto){
        Optional<Book> bookEntity = bookRepository.findById(id);
        if(!bookEntity.isEmpty()){
            bookEntity.get().update(dto.getTitle(), dto.getAuthor());
            return bookEntity.get().toDto();
        }else {
            throw new RuntimeException("해당 아이디를 찾을 수 없습니다.");
        }
    }
}
