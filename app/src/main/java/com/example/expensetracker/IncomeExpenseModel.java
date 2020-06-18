package com.example.expensetracker;

import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;

public class IncomeExpenseModel {
    private int id;
    private IncomeExpense incomeExpense;
    private String category;
    private double amount;
    private LocalDate date;

    public IncomeExpenseModel(int id, IncomeExpense incomeExpense, String category, double amount, LocalDate date) {
        this.id = id;
        this.incomeExpense = incomeExpense;
        this.category = category;
        this.amount = amount;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public IncomeExpense getIncomeExpense() {
        return incomeExpense;
    }

    public void setIncomeExpense(IncomeExpense incomeExpense) {
        this.incomeExpense = incomeExpense;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "IncomeExpenseModel{" +
                "id=" + id +
                ", incomeExpense=" + incomeExpense +
                ", category='" + category + '\'' +
                ", amount=" + amount +
                ", date=" + date +
                '}';
    }
}
