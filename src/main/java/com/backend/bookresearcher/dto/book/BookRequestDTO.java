package com.backend.bookresearcher.dto.book;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
public class BookRequestDTO {
    @NotBlank private String title;
    private String authors;
    @NotBlank private String description;
    private String theme;
    private String type;
    private String isbn;
    private String language;
    @Positive
    private Integer pageCount;
    private String coverUrl;
    private LocalDate publishingDate;
}
