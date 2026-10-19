package com.backend.bookresearcher.repository;

import com.backend.bookresearcher.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    @EntityGraph(attributePaths = {"user", "book"})
    Page<Review> findByBookId(Long bookId, Pageable pageable);

    @EntityGraph(attributePaths = {"user", "book"})
    Page<Review> findByUserId(Long userId, Pageable pageable);

    @EntityGraph(attributePaths = {"user", "book"})
    Optional<Review> findWithUserAndBookById(Long id);

    boolean existsByUserIdAndBookId(Long userId, Long bookId);

    @Query("SELECT AVG(r.grade) FROM Review r WHERE r.book.id = :bookId")
    Double findAverageGradeByBookId(@Param("bookId") Long bookId);

    long countByBookId(Long bookId);
}