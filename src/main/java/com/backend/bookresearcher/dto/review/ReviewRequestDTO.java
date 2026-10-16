package com.backend.bookresearcher.dto.review;

import com.backend.bookresearcher.enums.BookStatus;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewRequestDTO {

    @NotBlank(message = "O título da resenha é obrigatório.")
    private String title;

    @NotBlank(message = "A descrição/conteúdo da resenha é obrigatória.")
    private String description;

    @Min(value = 1, message = "A nota mínima é 1.")
    @Max(value = 5, message = "A nota máxima é 5.")
    private int grade;

    @NotNull(message = "O status de leitura (status) é obrigatório.")
    private BookStatus status;

    @NotNull(message = "O ID do usuário é obrigatório.")
    private Long userId;

    @NotNull(message = "O ID do livro é obrigatório.")
    private Long bookId;
}
