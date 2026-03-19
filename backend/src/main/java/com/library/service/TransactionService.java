package com.library.service;

import com.library.model.Book;
import com.library.model.Transaction;
import com.library.model.User;
import com.library.repository.BookRepository;
import com.library.repository.TransactionRepository;
import com.library.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;

@Service
public class TransactionService {

    private static final int DEFAULT_LOAN_DAYS = 14;

    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;
    private final FineService fineService;

    public TransactionService(
            TransactionRepository transactionRepository,
            UserRepository userRepository,
            BookRepository bookRepository,
            FineService fineService
    ) {
        this.transactionRepository = transactionRepository;
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
        this.fineService = fineService;
    }

    @Transactional
    public Transaction issueBook(Long userId, Long bookId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found"));

        Integer available = book.getAvailableCopies();
        if (available == null || available <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Book is not available");
        }

        if (transactionRepository.existsActiveIssue(userId, bookId)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "This user already has this book issued");
        }

        LocalDate issueDate = LocalDate.now();
        LocalDate dueDate = issueDate.plusDays(DEFAULT_LOAN_DAYS);

        Transaction tx = new Transaction();
        tx.setUser(user);
        tx.setBook(book);
        tx.setIssueDate(issueDate);
        tx.setDueDate(dueDate);
        tx.setReturnDate(null);
        tx.setFineAmount(0.0);

        book.setAvailableCopies(available - 1);
        bookRepository.save(book);
        return transactionRepository.save(tx);
    }

    @Transactional
    public Transaction returnBook(Long transactionId) {
        Transaction tx = transactionRepository.findById(transactionId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Transaction not found"));

        if (tx.getReturnDate() != null) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Book already returned for this transaction");
        }

        Book book = tx.getBook();
        if (book == null) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Transaction is missing book reference");
        }

        LocalDate returnDate = LocalDate.now();
        tx.setReturnDate(returnDate);
        double fine = fineService.calculate(tx.getDueDate(), returnDate);
        tx.setFineAmount(fine);

        Integer available = book.getAvailableCopies();
        Integer total = book.getTotalCopies();
        int nextAvailable = (available == null ? 0 : available) + 1;
        if (total != null && nextAvailable > total) {
            nextAvailable = total;
        }
        book.setAvailableCopies(nextAvailable);
        bookRepository.save(book);

        return transactionRepository.save(tx);
    }

    public Transaction getById(Long id) {
        return transactionRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Transaction not found"));
    }

    public List<Transaction> listAll() {
        return transactionRepository.findAll();
    }

    public List<Transaction> listByUser(Long userId) {
        return transactionRepository.findByUserId(userId);
    }

    public List<Transaction> listByBook(Long bookId) {
        return transactionRepository.findByBookId(bookId);
    }
}

