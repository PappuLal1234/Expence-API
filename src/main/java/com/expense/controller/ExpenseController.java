package com.expense.controller;

import com.expense.entity.Expense;
import com.expense.service.ExpenseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ExpenseController {
    @Autowired
    private ExpenseService expenseService;

    // Other methods...

    @PostMapping("/expenses")
    public ResponseEntity<Expense> createExpense(@RequestBody @Valid Expense expense) {
        Expense createdExpense = expenseService.createExpense(expense);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdExpense);
    }

    @GetMapping("/expenses/{expenseId}")
    public ResponseEntity<Expense> getExpenseById(@PathVariable Long expenseId) {
        Expense expense = expenseService.getExpenseById(expenseId);
        if (expense == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(expense);
    }

    @PutMapping("/expenses/{expenseId}")
    public ResponseEntity<Expense> updateExpense(@PathVariable Long expenseId, @RequestBody @Valid Expense updatedExpense) {
        Expense existingExpense = expenseService.getExpenseById(expenseId);
        if (existingExpense == null) {
            return ResponseEntity.notFound().build();
        }
        Expense updated = expenseService.updateExpense(existingExpense, updatedExpense);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/expenses/{expenseId}")
    public ResponseEntity<Void> deleteExpense(@PathVariable Long expenseId) {
        Expense existingExpense = expenseService.getExpenseById(expenseId);
        if (existingExpense == null) {
            return ResponseEntity.notFound().build();
        }
        expenseService.deleteExpense(expenseId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/expenses/report/month")
    public ResponseEntity<Double> getExpenseReportByMonth(@RequestParam String userId, @RequestParam int month) {
        double totalExpenditure = expenseService.getTotalExpenditureByMonth(userId, month);
        return ResponseEntity.ok(totalExpenditure);
    }

    @GetMapping("/expenses/report/week")
    public ResponseEntity<List<Expense>> getExpenseReportByWeek(@RequestParam String userId, @RequestParam int weekNumber, @RequestParam int year) {
        List<Expense> expenses = expenseService.getExpensesByWeek(userId, weekNumber, year);
        return ResponseEntity.ok(expenses);
    }

    // You can add more methods here as needed
}
//
//import com.expense.entity.Expense;
//import com.expense.entity.User;
//import com.expense.service.AuthService;
//import com.expense.service.ExpenseService;
//import com.expense.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.format.annotation.DateTimeFormat;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.time.LocalDate;
//import java.util.List;
//
//@RestController
//@RequestMapping("/api")
//public class ExpenseController {
//    @Autowired
//    private ExpenseService expenseService;
//
//    @Autowired
//    private UserService userService;
//
//    @Autowired
//    private AuthService authService;
//
//    @GetMapping("/expenses")
//    public ResponseEntity<List<Expense>> getExpensesByUserAndDate(@RequestParam String username,
//                                                                  @RequestParam
//                                                                  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
//                                                                  @RequestParam String token) {
//        User user = userService.findUserByUsername(username);
//        if (user == null) {
//            return ResponseEntity.notFound().build();
//        }
//
//        User authenticatedUser = authService.getAuthenticatedUser(token);
//        if (authenticatedUser == null || !authenticatedUser.equals(user)) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
//        }
//
//        List<Expense> expenses = expenseService.getExpensesByUserAndDate(user, date);
//        return ResponseEntity.ok(expenses);
//    }
//
//    // Add other API endpoints for expense CRUD operations and report generation
//}
