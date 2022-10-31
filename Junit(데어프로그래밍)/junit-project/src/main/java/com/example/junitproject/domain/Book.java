package com.example.junitproject.domain;

import com.example.junitproject.web.dto.BookResponseDto;
import com.example.junitproject.web.dto.BookSaveReqDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Book {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, nullable = false)
    private String title;

    @Column(length = 20, nullable = false)
    private String author;

    @Builder
    public Book(Long id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
    }

    public void update(String title, String author) {
        this.title = title;
        this.author = author;
    }

    public BookResponseDto toDto(){
        return BookResponseDto.builder()
                .id(id)
                .title(title)
                .author(author)
                .build();
    }
}
