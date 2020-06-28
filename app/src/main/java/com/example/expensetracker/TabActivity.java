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
        final SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);


        //Update the change in tabs and show the update when switch the tabs
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        System.out.println("save goal!");
                        sectionsPagerAdapter.notifyDataSetChanged();
                    case 1:
                        System.out.println("income expense");
                        sectionsPagerAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

}
