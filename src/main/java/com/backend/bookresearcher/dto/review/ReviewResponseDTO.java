package com.backend.bookresearcher.dto.review;

import com.backend.bookresearcher.entity.Review;
import com.backend.bookresearcher.enums.BookStatus;
import lombok.Getter;
import java.time.LocalDateTime;

@Getter
public class ReviewResponseDTO {
    private Long id;
    private String title;
    private String description;
    private int grade;
    private BookStatus status;
    private LocalDateTime createdAt;
    private UserSummaryDTO user;
    private BookSummaryDTO book;

    public ReviewResponseDTO(Review review) {
        this.id = review.getId();
        this.title = review.getTitle();
        this.description = review.getDescription();
        this.grade = review.getGrade();
        this.status = review.getStatus();
        this.createdAt = review.getCreatedAt();

        if (review.getUser() != null) {
            this.user = new UserSummaryDTO(review.getUser().getId(), review.getUser().getName());
        }
        if (review.getBook() != null) {
            this.book = new BookSummaryDTO(review.getBook().getId(), review.getBook().getTitle());
        }
    }

    // Filter data, only sends necessary info.
    @Getter
    public static class UserSummaryDTO {
        private Long id;
        private String name;

        public UserSummaryDTO(Long id, String name) {
            this.id = id;
            this.name = name;
        }
    }

    @Getter
    public static class BookSummaryDTO {
        private Long id;
        private String title;

        public BookSummaryDTO(Long id, String title) {
            this.id = id;
            this.title = title;
        }
    }
}
