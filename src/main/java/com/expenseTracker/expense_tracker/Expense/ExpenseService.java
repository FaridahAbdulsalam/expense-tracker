package com.expenseTracker.expense_tracker.Expense;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ExpenseService {
    private final ExpenseRepository expenseRepository;

    public ExpenseService(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }


    public List<Expense> getAllExpenses(Long userId){
        return expenseRepository.findAllByUserId(userId);
    }

    public List<Expense>getExpenses(Long userId, LocalDate startDate, LocalDate endDate){
        if(startDate == null || endDate == null){
            return getAllExpenses(userId);
        }
        return expenseRepository.findByUserIdAndDate(userId, startDate, endDate);
    }

    public List<Expense> getExpensesByVendor(Long userId, String vendor){
        return expenseRepository.findByUserIdAndVendor(userId, vendor);
    }

    public void addExpense(Expense expense){
        expenseRepository.save(expense);
    }
}
