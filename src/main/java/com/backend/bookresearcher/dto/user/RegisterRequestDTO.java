package com.backend.bookresearcher.dto.user;

import com.backend.bookresearcher.enums.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequestDTO {
    @NotBlank(message = "O nome é obrigatório.")
    private String name;

    @NotBlank @Email(message = "O e-mail deve ser válido.")
    private String email;

    @NotBlank @Size(min = 8, message = "A senha deve ter ao menos 8 caracteres.")
    private String password;

    @NotNull(message = "O cargo (role) é obrigatório.")
    private UserRole role;
}
