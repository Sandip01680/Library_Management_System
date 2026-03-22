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