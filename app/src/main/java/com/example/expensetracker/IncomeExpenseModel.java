package com.example.expensetracker;

import java.util.Date;

public class IncomeExpenseModel {
    private int id;
    private IncomeExpense incomeExpense;
    private String category;
    private double amount;
    private Date date;

    public IncomeExpenseModel(int id, IncomeExpense incomeExpense, String category, double amount, Date date) {
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
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
