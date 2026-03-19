package com.library.repository;

import com.library.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findByUserId(Long userId);

    List<Transaction> findByBookId(Long bookId);

    @Query("select count(t) > 0 from Transaction t where t.user.id = :userId and t.book.id = :bookId and t.returnDate is null")
    boolean existsActiveIssue(@Param("userId") Long userId, @Param("bookId") Long bookId);
}

