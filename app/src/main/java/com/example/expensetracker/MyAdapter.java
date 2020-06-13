package com.example.expensetracker;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class MyAdapter extends FragmentPagerAdapter {
    private Context myContext;
    int totalTabs;

    public MyAdapter(Context context, FragmentManager fm,int totalTabs) {
        super(fm);
        myContext = context;
        this.totalTabs = totalTabs;
    }

    //Fragment tabs
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                SaveGoalFragment saveGoalFragment = new SaveGoalFragment();
                return saveGoalFragment;
            case 1:
                IncomeExpenseFragment incomeExpenseFragment = new IncomeExpenseFragment();
                return incomeExpenseFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return totalTabs;
    }
}
