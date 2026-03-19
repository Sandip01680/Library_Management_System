package com.library.service;

import com.library.model.Book;
import com.library.repository.BookRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Transactional
    public Book create(Book book) {
        if (book.getIsbn() != null && !book.getIsbn().isBlank() && bookRepository.existsByIsbn(book.getIsbn())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "ISBN already exists");
        }

        if (book.getAvailableCopies() == null && book.getTotalCopies() != null) {
            book.setAvailableCopies(book.getTotalCopies());
        }
        validateCopies(book.getTotalCopies(), book.getAvailableCopies());

        return bookRepository.save(book);
    }

    public List<Book> list() {
        return bookRepository.findAll();
    }

    public Book getById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found"));
    }

    @Transactional
    public Book update(Long id, Book patch) {
        Book existing = getById(id);

        if (patch.getTitle() != null) existing.setTitle(patch.getTitle());
        if (patch.getAuthor() != null) existing.setAuthor(patch.getAuthor());

        if (patch.getIsbn() != null && !patch.getIsbn().equals(existing.getIsbn())) {
            if (!patch.getIsbn().isBlank() && bookRepository.existsByIsbn(patch.getIsbn())) {
                throw new ResponseStatusException(HttpStatus.CONFLICT, "ISBN already exists");
            }
            existing.setIsbn(patch.getIsbn());
        }

        if (patch.getTotalCopies() != null) existing.setTotalCopies(patch.getTotalCopies());
        if (patch.getAvailableCopies() != null) existing.setAvailableCopies(patch.getAvailableCopies());
        validateCopies(existing.getTotalCopies(), existing.getAvailableCopies());

        return bookRepository.save(existing);
    }

    @Transactional
    public void delete(Long id) {
        if (!bookRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found");
        }
        bookRepository.deleteById(id);
    }

    private static void validateCopies(Integer total, Integer available) {
        if (total == null || available == null) return;
        if (available > total) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "availableCopies cannot be greater than totalCopies");
        }
    }
}

