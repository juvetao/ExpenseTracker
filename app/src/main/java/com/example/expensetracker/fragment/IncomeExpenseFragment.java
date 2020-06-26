package com.example.expensetracker.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.expensetracker.MoreIncomeExpenseActionsActivity;
import com.example.expensetracker.R;
import com.example.expensetracker.dbhelper.ExpenseTrackerDBHelper;
import com.example.expensetracker.model.IncomeExpenseModel;

public class IncomeExpenseFragment extends Fragment {

    private ExpenseTrackerDBHelper myDBHelper;
    private ArrayAdapter adp;
    private ListView listView;
    private Button more_action_btn;

    public IncomeExpenseFragment() {
    }


    //onCreate here is responsible for calling back onCreateOptionsMenu method just like in an Activity
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

    }

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_income_expense, container, false);
        myDBHelper = new ExpenseTrackerDBHelper(getActivity());
        listView = view.findViewById(R.id.list_view_income_expense);

        more_action_btn = view.findViewById(R.id.more_action_btn);
        updateViews();
        more_action_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MoreIncomeExpenseActionsActivity.class);
                startActivity(intent);
            }
        });

        updateViews();

        return view;
    }

    private void updateViews(){
        adp = new ArrayAdapter<IncomeExpenseModel>(getActivity(), android.R.layout.simple_list_item_1, myDBHelper.getAllIncomeExpenses());
        listView.setAdapter(adp);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void onPause() {
        super.onPause();
        updateViews();
    }
}
