package com.backend.bookresearcher.repository;

import com.backend.bookresearcher.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    @Query("""
        SELECT b FROM Book b
        WHERE LOWER(b.title)   LIKE LOWER(CONCAT('%', :q, '%'))
           OR LOWER(b.authors) LIKE LOWER(CONCAT('%', :q, '%'))
        """)
    Page<Book> search(@Param("q") String query, Pageable pageable);

    boolean existsBySourceAndExternalId(String source, String externalId);

    Optional<Book> findBySourceAndExternalId(String source, String externalId);

    boolean existsByIsbn(String isbn);
}