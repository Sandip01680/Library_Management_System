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

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    public record BookRequest(@NotBlank String title,
                              @NotBlank String author,
                              @NotBlank String isbn,
                              @NotNull @PositiveOrZero Integer totalCopies,
                              @PositiveOrZero Integer availableCopies) {}

    @PostMapping
    public ResponseEntity<Book> create(@Valid @RequestBody BookRequest body) {
        Book book = new Book();
        book.setTitle(body.title());
        book.setAuthor(body.author());
        book.setIsbn(body.isbn());
        book.setTotalCopies(body.totalCopies());
        book.setAvailableCopies(body.availableCopies());

        return ResponseEntity.status(HttpStatus.CREATED).body(bookService.create(book));
    }

    @GetMapping
    public List<Book> list() {
        return bookService.list();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        bookService.delete(id);
        return ResponseEntity.ok("Book deleted successfully");
    }
}