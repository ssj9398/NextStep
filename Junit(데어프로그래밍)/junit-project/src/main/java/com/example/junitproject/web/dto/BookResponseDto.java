package com.example.junitproject.web.dto;

import com.example.junitproject.domain.Book;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BookResponseDto {
    private Long id;
    private String title;
    private String author;

    public BookResponseDto toDto(Book book){
        this.id = book.getId();
        this.title = book.getTitle();
        this.author = book.getAuthor();
        return this;
    }
}
