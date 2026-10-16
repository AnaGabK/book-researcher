package com.backend.bookresearcher.dto.book;

import com.backend.bookresearcher.entity.Book;
import lombok.Getter;
import java.time.LocalDate;

@Getter
public class BookResponseDTO {
    private Long id;
    private String title;
    private String bio;
    private String theme;
    private String tipo;
    private LocalDate publishingDate;

    public BookResponseDTO(Book book) {
        this.id = book.getId();
        this.title = book.getTitle();
        this.bio = book.getBio();
        this.theme = book.getTheme();
        this.tipo = book.getTipo();
        this.publishingDate = book.getPublishingDate();
    }
}
