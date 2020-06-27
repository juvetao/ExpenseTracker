package com.example.expensetracker;


import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;

import com.example.expensetracker.dbhelper.ExpenseTrackerDBHelper;
import com.example.expensetracker.fragment.IncomeExpenseFragment;
import com.example.expensetracker.fragment.SaveGoalFragment;
import com.example.expensetracker.model.IncomeExpenseModel;
import com.example.expensetracker.model.SaveGoalModel;
import com.google.android.material.tabs.TabLayout;

import androidx.annotation.NonNull;
import android.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;


import com.example.expensetracker.ui.main.SectionsPagerAdapter;

public class TabActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);

    }

//    @Override
//    public void sendSumData(double totalIncome, double totalExpense) {
//        String tag = "android:switcher:" + 2131231003 + ":" + 0;
//        SaveGoalFragment f = (SaveGoalFragment) getSupportFragmentManager().findFragmentByTag(tag);
//        f.receivedData(totalIncome, totalExpense);
//    }

    private void loadFragment(Fragment fragment) {
// create a FragmentManager
        FragmentManager fm = getFragmentManager();
// create a FragmentTransaction to begin the transaction and replace the Fragment
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
            fragmentTransaction.replace(R.id.save_goal_frag, fragment);
            fragmentTransaction.commit(); // save the changes
    }
}
