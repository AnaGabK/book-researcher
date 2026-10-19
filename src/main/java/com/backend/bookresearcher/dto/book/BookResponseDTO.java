package com.backend.bookresearcher.dto.book;

import com.backend.bookresearcher.entity.Book;
import lombok.Getter;
import java.time.LocalDate;

@Getter
public class BookResponseDTO {
    private Long id;
    private String title, authors, description, theme, type, isbn, language, coverUrl;
    private Integer pageCount;
    private LocalDate publishingDate;

    public BookResponseDTO(Book b) {
        this.id = b.getId();
        this.title = b.getTitle();
        this.authors = b.getAuthors();
        this.description = b.getDescription();
        this.theme = b.getTheme();
        this.type = b.getType();
        this.isbn = b.getIsbn();
        this.language = b.getLanguage();
        this.pageCount = b.getPageCount();
        this.coverUrl = b.getCoverUrl();
        this.publishingDate = b.getPublishingDate();
    }
}
