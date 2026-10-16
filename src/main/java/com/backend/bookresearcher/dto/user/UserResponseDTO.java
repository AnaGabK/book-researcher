package com.backend.bookresearcher.dto.user;

import com.backend.bookresearcher.enums.UserRole;
import com.backend.bookresearcher.entity.User;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class UserResponseDTO {
    private Long id;
    private String name;
    private String email;
    private String profileImg;
    private UserRole role;
    private LocalDateTime createdAt;

    public UserResponseDTO(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.profileImg = user.getProfileImg();
        this.role = user.getRole();
        this.createdAt = user.getCreatedAt();
    }
}
