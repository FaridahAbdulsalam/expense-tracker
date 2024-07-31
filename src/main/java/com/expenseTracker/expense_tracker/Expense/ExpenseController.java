package com.expenseTracker.expense_tracker.Expense;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/expenses")
public class ExpenseController {
    @Autowired
   private ExpenseService expenseService;


    @PostMapping("/add")
    public void addExpense(@RequestBody Expense expense){
        expenseService.addExpense(expense);
    }

    @GetMapping
    public List<Expense> getExpenses(@RequestParam Long userId,
                                     @RequestParam(required = false)LocalDate startDate,
                                     @RequestParam(required = false)LocalDate endDate){
        return expenseService.getExpenses(userId, startDate, endDate);
    }

    @GetMapping("/vendor")
    public List<Expense> getExpensesByVendor(@RequestParam Long userId,
                                             @RequestParam String vendor){
        return expenseService.getExpensesByVendor(userId,vendor);
    }
}
