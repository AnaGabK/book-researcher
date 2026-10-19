package com.backend.bookresearcher.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "books",
        uniqueConstraints = @UniqueConstraint(columnNames = {"source", "external_id"}))
@Getter @Setter
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    private String authors;

    @Column(columnDefinition = "TEXT")
    private String description;

    private String theme;
    private String type;
    private String isbn;
    private String language;

    @Column(name = "page_count")
    private Integer pageCount;

    @Column(name = "cover_url", length = 500)
    private String coverUrl;

    @Column(name = "publishing_date")
    private LocalDate publishingDate;

    @Column(length = 30)
    private String source;                // "OPEN_LIBRARY"

    @Column(name = "external_id")
    private String externalId;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> reviews;
}