package com.backend.bookresearcher.service;

import com.backend.bookresearcher.dto.book.BookRequestDTO;
import com.backend.bookresearcher.dto.book.BookResponseDTO;
import com.backend.bookresearcher.dto.common.PageResponse;
import com.backend.bookresearcher.entity.Book;
import com.backend.bookresearcher.exception.ConflictException;
import com.backend.bookresearcher.exception.ResourceNotFoundException;
import com.backend.bookresearcher.repository.BookRepository;
import com.backend.bookresearcher.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BookService {

    private static final String SOURCE_MANUAL = "MANUAL";
    private static final Set<String> SORTABLE_FIELDS =
            Set.of("id", "title", "authors", "publishingDate");
    private final BookRepository bookRepository;

    public PageResponse<BookResponseDTO> list(String search, Pageable pageable) {
        validateSort(pageable);

        Page<Book> page = (search == null || search.isBlank())
                ? bookRepository.findAll(pageable)
                : bookRepository.search(search.trim(), pageable);

        return PageResponse.from(page, BookResponseDTO::new);
    }

    private void validateSort(Pageable pageable) {
        for (Sort.Order order : pageable.getSort()) {
            if (!SORTABLE_FIELDS.contains(order.getProperty())) {
                throw new BusinessException("Campo de ordenação inválido: " + order.getProperty());
            }
        }
    }

    public BookResponseDTO findById(Long id) {
        return new BookResponseDTO(getOrThrow(id));
    }

    @Transactional
    public BookResponseDTO create(BookRequestDTO dto) {
        String isbn = normalize(dto.getIsbn());
        if (isbn != null && bookRepository.existsByIsbn(isbn)) {
            throw new ConflictException("Já existe um livro com este ISBN.");
        }

        Book book = new Book();
        applyFields(book, dto);
        book.setIsbn(isbn);
        book.setSource(SOURCE_MANUAL);

        return new BookResponseDTO(bookRepository.save(book));
    }

    @Transactional
    public BookResponseDTO update(Long id, BookRequestDTO dto) {
        Book book = getOrThrow(id);

        String isbn = normalize(dto.getIsbn());
        boolean isbnChanged = isbn != null && !isbn.equals(book.getIsbn());
        if (isbnChanged && bookRepository.existsByIsbn(isbn)) {
            throw new ConflictException("Já existe um livro com este ISBN.");
        }

        applyFields(book, dto);
        book.setIsbn(isbn);

        // No save() needed: the entity is managed, so the transaction flushes the changes
        return new BookResponseDTO(book);
    }

    @Transactional
    public void delete(Long id) {
        bookRepository.delete(getOrThrow(id));
    }

    //Helpers

    private Book getOrThrow(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Livro não encontrado: " + id));
    }

    private void applyFields(Book book, BookRequestDTO dto) {
        book.setTitle(dto.getTitle().trim());
        book.setAuthors(normalize(dto.getAuthors()));
        book.setDescription(dto.getDescription());
        book.setTheme(normalize(dto.getTheme()));
        book.setType(normalize(dto.getType()));
        book.setLanguage(normalize(dto.getLanguage()));
        book.setPageCount(dto.getPageCount());
        book.setCoverUrl(normalize(dto.getCoverUrl()));
        book.setPublishingDate(dto.getPublishingDate());
    }

    private String normalize(String value) {
        return (value == null || value.isBlank()) ? null : value.trim();
    }
}