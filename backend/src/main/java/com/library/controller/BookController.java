package com.library.controller;

import com.library.model.Book;
import com.library.service.BookService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    public record BookRequest(
            @NotBlank String title,
            @NotBlank String author,
            @NotBlank String isbn,
            @NotNull @PositiveOrZero Integer totalCopies,
            @PositiveOrZero Integer availableCopies
    ) {}

    public record BookPatchRequest(
            String title,
            String author,
            String isbn,
            @PositiveOrZero Integer totalCopies,
            @PositiveOrZero Integer availableCopies
    ) {}

    @PostMapping
    public ResponseEntity<Book> create(@Valid @RequestBody BookRequest body) {
        Book book = new Book();
        book.setTitle(body.title());
        book.setAuthor(body.author());
        book.setIsbn(body.isbn());
        book.setTotalCopies(body.totalCopies());
        book.setAvailableCopies(body.availableCopies());
        Book created = bookService.create(book);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping
    public List<Book> list() {
        return bookService.list();
    }

    @GetMapping("/{id}")
    public Book getById(@PathVariable Long id) {
        return bookService.getById(id);
    }

    @PutMapping("/{id}")
    public Book update(@PathVariable Long id, @Valid @RequestBody BookPatchRequest body) {
        Book patch = new Book();
        patch.setTitle(body.title());
        patch.setAuthor(body.author());
        patch.setIsbn(body.isbn());
        patch.setTotalCopies(body.totalCopies());
        patch.setAvailableCopies(body.availableCopies());
        return bookService.update(id, patch);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        bookService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

