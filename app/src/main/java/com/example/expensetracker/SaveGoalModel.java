package com.example.expensetracker;

public class SaveGoalModel {
    private int id;
    private String goal_name;
    private double total_amount;
    private int period_length;

    public SaveGoalModel(int id, String goal_name, double total_amount, int period_length) {
        this.id = id;
        this.goal_name = goal_name;
        this.total_amount = total_amount;
        this.period_length = period_length;
    }

    public int getId() {
        return id;
    }

    public String getGoal_name() {
        return goal_name;
    }

    public void setGoal_name(String goal_name) {
        this.goal_name = goal_name;
    }


    public double getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(double total_amount) {
        this.total_amount = total_amount;
    }

    public int getPeriod_length() {
        return period_length;
    }

    public void setPeriod_length(int period_length) {
        this.period_length = period_length;
    }

    @Override
    public String toString() {
        return "SaveGoalModel{" +
                "id=" + id +
                ", goal_name='" + goal_name + '\'' +
                ", total_amount=" + total_amount +
                ", period_length=" + period_length +
                '}';
    }
}
