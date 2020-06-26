package com.example.expensetracker;


import android.os.Bundle;

import com.example.expensetracker.dbhelper.ExpenseTrackerDBHelper;
import com.example.expensetracker.model.IncomeExpenseModel;
import com.example.expensetracker.model.SaveGoalModel;
import com.google.android.material.tabs.TabLayout;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;


import com.example.expensetracker.ui.main.SectionsPagerAdapter;

public class TabActivity extends AppCompatActivity {

//    private ExpenseTrackerDBHelper myDBHelper;
//
//    private SaveGoalModel save_goal_1 = new SaveGoalModel(1, "Trip", 30000.0, 12);
//    private SaveGoalModel save_goal_2 = new SaveGoalModel(2, "House", 3000000.0, 60);
//
//    private IncomeExpenseModel income_1 = new IncomeExpenseModel(1, "Income", "Salary", 20000.0, 200625);
//    private IncomeExpenseModel expense_1 = new IncomeExpenseModel(2, "Expense", "Shopping", 1050.0, 200412);
//    private IncomeExpenseModel expense_2 = new IncomeExpenseModel(3, "Expense", "El", 383.0, 200401);
//    private IncomeExpenseModel expense_3 = new IncomeExpenseModel(4, "Expense", "Broadband", 299.0, 200501);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);

//        myDBHelper = new ExpenseTrackerDBHelper(this);
//
//        myDBHelper.resetDatabase();
//
//        myDBHelper.addSaveGoalToDb(save_goal_1);
//        myDBHelper.addSaveGoalToDb(save_goal_2);
//        myDBHelper.addIncomeExpenseToDb(income_1);
//        myDBHelper.addIncomeExpenseToDb(expense_1);
//        myDBHelper.addIncomeExpenseToDb(expense_2);
//        myDBHelper.addIncomeExpenseToDb(expense_3);
    }

}