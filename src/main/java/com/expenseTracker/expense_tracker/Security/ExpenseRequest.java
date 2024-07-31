package com.expenseTracker.expense_tracker.Security;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter @Setter
public class ExpenseRequest {
    private Long userId;
    private String category;
    private BigDecimal amount;
    private LocalDate date;
    private String vendor;
}
