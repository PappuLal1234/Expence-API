package com.expense.repo;

import com.expense.entity.Expense;
import com.expense.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    List<Expense> findByUserAndDate(User user, LocalDate date);

    // Add other custom queries as needed
}

