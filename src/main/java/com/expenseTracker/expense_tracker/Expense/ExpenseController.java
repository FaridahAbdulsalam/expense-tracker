package com.expenseTracker.expense_tracker.Expense;

import com.expenseTracker.expense_tracker.Security.ExpenseData;
import com.expenseTracker.expense_tracker.Security.ExpenseRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/expenses")
public class ExpenseController {
    @Autowired
   private ExpenseService expenseService;


    @PostMapping("/add")
    public ResponseEntity<String> addExpense(@RequestBody ExpenseRequest expenseRequest){
        try{
            Expense expense = new Expense();
            expense.setCategory(expenseRequest.getCategory());
            expense.setAmount(expenseRequest.getAmount());
            expense.setDate(expenseRequest.getDate());
            expense.setVendor(expenseRequest.getVendor());

            expenseService.addExpense(expense, expenseRequest.getUserId());
            return ResponseEntity.ok("Expense Added Successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping
    public List<ExpenseData> getExpenses(@RequestParam Long userId,
                                         @RequestParam(required = false)LocalDate startDate,
                                         @RequestParam(required = false)LocalDate endDate){
        return expenseService.getExpenses(userId, startDate, endDate);
    }

    @GetMapping("/category")
    public List<ExpenseData> getExpensesByVendor(@RequestParam Long userId,
                                             @RequestParam String category){
        return expenseService.getExpensesByCategory(userId,category);
    }
}
