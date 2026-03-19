package com.library.controller;

import com.library.model.Transaction;
import com.library.service.TransactionService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    public record IssueRequest(@NotNull Long userId, @NotNull Long bookId) {}

    @PostMapping("/issue")
    public ResponseEntity<Transaction> issue(@Valid @RequestBody IssueRequest body) {
        Transaction tx = transactionService.issueBook(body.userId(), body.bookId());
        return ResponseEntity.status(HttpStatus.CREATED).body(tx);
    }

    @PostMapping("/return/{transactionId}")
    public Transaction returnBook(@PathVariable Long transactionId) {
        return transactionService.returnBook(transactionId);
    }

    @GetMapping
    public List<Transaction> listAll() {
        return transactionService.listAll();
    }

    @GetMapping("/{id}")
    public Transaction getById(@PathVariable Long id) {
        return transactionService.getById(id);
    }

    @GetMapping("/user/{userId}")
    public List<Transaction> listByUser(@PathVariable Long userId) {
        return transactionService.listByUser(userId);
    }

    @GetMapping("/book/{bookId}")
    public List<Transaction> listByBook(@PathVariable Long bookId) {
        return transactionService.listByBook(bookId);
    }
}

