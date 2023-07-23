package com.expense.service;

import com.expense.entity.Expense;
import com.expense.repo.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class ExpenseService {
    @Autowired
    private ExpenseRepository expenseRepository;

    // Other methods...

    public Expense createExpense(Expense expense) {
        return expenseRepository.save(expense);
    }

    public Optional<Expense> getAllExpensesByUser(Long userId) {
        return expenseRepository.findById(userId);

    }

    public Expense getExpenseById(Long expenseId) {
        return expenseRepository.findById(expenseId).orElse(null);
    }

    public Expense updateExpense(Expense existingExpense, Expense updatedExpense) {
        existingExpense.setTitle(updatedExpense.getTitle());
        existingExpense.setDescription(updatedExpense.getDescription());
        existingExpense.setPrice(updatedExpense.getPrice());
        existingExpense.setDate(updatedExpense.getDate());
        // You can update other fields as needed
        return expenseRepository.save(existingExpense);
    }

    public void deleteExpense(Long expenseId) {
        expenseRepository.deleteById(expenseId);
    }

    public List<Expense> getExpensesByDateAndTime(LocalDateTime dateTime) {
        // Implement the logic to fetch expenses based on the given date and time
        // For example, you can use a custom query to filter expenses based on date and time
        // return expenseRepository.findByDateAndTime(dateTime);
        return Collections.emptyList(); // Replace this with actual implementation
    }

    public double getTotalExpenditureByMonth(String userId, int month) {
        // Implement the logic to calculate the total expenditure for the given user and month
        // You may need to fetch the user's expenses for the specified month and sum their prices
        // return expenseRepository.getTotalExpenditureByMonth(userId, month);
        return 0.0; // Replace this with actual implementation
    }

    // Additional methods for generating expense reports

    public List<Expense> getExpensesByMonthAndYear(String userId, int month, int year) {
        // Implement the logic to fetch expenses for the given user, month, and year
        // return expenseRepository.findByUserIdAndMonthAndYear(userId, month, year);
        return Collections.emptyList(); // Replace this with actual implementation
    }

    public List<Expense> getExpensesByWeek(String userId, int weekNumber, int year) {
        // Implement the logic to fetch expenses for the given user, week, and year
        // return expenseRepository.findByUserIdAndWeekNumberAndYear(userId, weekNumber, year);
        return Collections.emptyList(); // Replace this with actual implementation
    }

    // You can add more methods here as needed
}
//
//import com.expense.entity.Expense;
//import com.expense.entity.User;
//import com.expense.repo.ExpenseRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.time.LocalDate;
//import java.util.List;
//
//@Service
//public class ExpenseService {
//    @Autowired
//    private ExpenseRepository expenseRepository;
//
//    public List<Expense> getExpensesByUserAndDate(User user, LocalDate date) {
//        return expenseRepository.findByUserAndDate(user, date);
//    }
//
//    // Add other service methods as needed
//}
