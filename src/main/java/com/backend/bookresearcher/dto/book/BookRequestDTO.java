package com.backend.bookresearcher.dto.book;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
public class BookRequestDTO {

    @NotBlank(message = "O título do livro é obrigatório.")
    private String title;

    @NotBlank(message = "A bio/descrição do livro é obrigatória.")
    private String bio;

    @NotBlank(message = "O tema do livro é obrigatório.")
    private String theme;

    @NotBlank(message = "O tipo do livro é obrigatório.")
    private String tipo;

    @NotNull(message = "A data de publicação é obrigatória.")
    private LocalDate publishingDate;
}
