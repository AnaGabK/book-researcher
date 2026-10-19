package com.backend.bookresearcher.controller;

import com.backend.bookresearcher.dto.book.BookRequestDTO;
import com.backend.bookresearcher.dto.book.BookResponseDTO;
import com.backend.bookresearcher.dto.common.PageResponse;
import com.backend.bookresearcher.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping
    public PageResponse<BookResponseDTO> list(
            @RequestParam(required = false) String search,
            @PageableDefault(size = 20, sort = "title") Pageable pageable) {
        return bookService.list(search, pageable);
    }

    @GetMapping("/{id}")
    public BookResponseDTO findById(@PathVariable Long id) {
        return bookService.findById(id);
    }

    // TODO: restrict to ADMIN once authentication exists
    @PostMapping
    public ResponseEntity<BookResponseDTO> create(@Valid @RequestBody BookRequestDTO dto) {
        BookResponseDTO created = bookService.create(dto);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.getId())
                .toUri();

        return ResponseEntity.created(location).body(created);
    }

    // TODO: restrict to ADMIN
    @PutMapping("/{id}")
    public BookResponseDTO update(@PathVariable Long id, @Valid @RequestBody BookRequestDTO dto) {
        return bookService.update(id, dto);
    }

    // TODO: restrict to ADMIN
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        bookService.delete(id);
        return ResponseEntity.noContent().build();
    }
}