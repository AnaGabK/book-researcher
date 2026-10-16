package com.backend.bookresearcher.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "books") // Pluralizado para seguir o padrão
@Getter
@Setter
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String bio;
    private String theme;
    private String tipo;

    @Column(name = "publishing_date")
    private LocalDate publishingDate;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> reviews; //List of reviews
}
