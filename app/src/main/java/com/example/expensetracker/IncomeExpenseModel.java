package com.example.expensetracker;

public class IncomeExpenseModel {
    private int id;
    private String incomeExpense;
    private String category;
    private double amount;
    private int date;

    public IncomeExpenseModel(int id, String incomeExpense, String category, double amount, int date) {
        this.id = id;
        this.incomeExpense = incomeExpense;
        this.category = category;
        this.amount = amount;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public String getIncomeExpense() {
        return incomeExpense;
    }

    public void setIncomeExpense(String incomeExpense) {
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

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
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
