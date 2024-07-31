package com.expenseTracker.expense_tracker.Expense;
import com.expenseTracker.expense_tracker.Security.ExpenseData;
import com.expenseTracker.expense_tracker.User.User;
import com.expenseTracker.expense_tracker.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ExpenseService {
    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    private UserRepository userRepository;


    public List<Expense> getAllExpenses(Long userId){
        return expenseRepository.findAllByUserId(userId);
    }

    public List<ExpenseData>getExpenses(Long userId, LocalDate startDate, LocalDate endDate){
        List<Expense> expenses = (startDate == null || endDate == null) ?
                getAllExpenses(userId) :
                expenseRepository.findByUserIdAndDate(userId, startDate, endDate);

        return expenses.stream().map(expense -> {
            ExpenseData data = new ExpenseData();
            data.setId(expense.getId());
            data.setUserId(expense.getUser().getId());
            data.setCategory(expense.getCategory());
            data.setAmount(expense.getAmount());
            data.setDate(expense.getDate());
            data.setVendor(expense.getVendor());
            return data;
        }).collect(Collectors.toList());
    }

    public List<ExpenseData> getExpensesByCategory(Long userId, String category){
        List<Expense> expenses = expenseRepository.findByUserIdAndCategory(userId, category);
        return expenses.stream().map(expense -> {
            ExpenseData data = new ExpenseData();
            data.setId(expense.getId());
            data.setUserId(expense.getUser().getId());
            data.setCategory(expense.getCategory());
            data.setAmount(expense.getAmount());
            data.setDate(expense.getDate());
            data.setVendor(expense.getVendor());
            return data;
        }).collect(Collectors.toList());
    }

    public void addExpense(Expense expense, Long userId) {
        // Check if the user exists
        Optional<User> user = userRepository.findById(userId);
        if (user.isEmpty()) {
            throw new RuntimeException("User not found");
        }
        expense.setUser(user.get());
        expenseRepository.save(expense);
    }

}
