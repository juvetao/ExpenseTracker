package com.example.expensetracker.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;


import androidx.fragment.app.Fragment;

import com.android.volley.RequestQueue;
import com.example.expensetracker.APIActivity;
import com.example.expensetracker.CreateNewSaveGoalActivity;
import com.example.expensetracker.R;
import com.example.expensetracker.dbhelper.ExpenseTrackerDBHelper;
import com.example.expensetracker.model.IncomeExpenseModel;
import com.example.expensetracker.model.SaveGoalModel;

public class SaveGoalFragment extends Fragment {
    private TextView save_goal_frag_txt;
    private ProgressBar progressBar;
    private Button create_new_save_goal_btn, api_btn;
    private RequestQueue reqQueue;
    private ExpenseTrackerDBHelper myDBHelper;
//
    private SaveGoalModel save_goal_1 = new SaveGoalModel(1, "Trip", 30000.0, 12);
    private SaveGoalModel save_goal_2 = new SaveGoalModel(2, "House", 3000000.0, 60);
//
//    private IncomeExpenseModel income_1 = new IncomeExpenseModel(1, "Income", "Salary", 20000.0, 200625);
//    private IncomeExpenseModel expense_1 = new IncomeExpenseModel(2, "Expense", "Shopping", 1050.0, 200412);
//    private IncomeExpenseModel expense_2 = new IncomeExpenseModel(3, "Expense", "El", 383.0, 200401);
//    private IncomeExpenseModel expense_3 = new IncomeExpenseModel(4, "Expense", "Broadband", 299.0, 200501);

    //To have the result after calculation
    private int percentage_of_goal;

    public SaveGoalFragment(){

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_save_goal, container, false);
        progressBar = (ProgressBar) view.findViewById(R.id.save_goal_frag_prog);

        save_goal_frag_txt = view.findViewById(R.id.save_goal_frag_txt);

        api_btn = (Button) view.findViewById(R.id.btn_api);
        api_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), APIActivity.class);
                startActivity(intent);
            }
        });

        create_new_save_goal_btn = (Button) view.findViewById(R.id.save_goal_frag_btn);
        create_new_save_goal_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CreateNewSaveGoalActivity.class);
                startActivity(intent);
            }
        });

        myDBHelper = new ExpenseTrackerDBHelper(getActivity());

        //Avoid data duplicated after going back to main activity
        myDBHelper.resetDatabase();
//
        myDBHelper.addSaveGoalToDb(save_goal_1);
        myDBHelper.addSaveGoalToDb(save_goal_2);
//        myDBHelper.addIncomeExpenseToDb(income_1);
//        myDBHelper.addIncomeExpenseToDb(expense_1);
//        myDBHelper.addIncomeExpenseToDb(expense_2);
//        myDBHelper.addIncomeExpenseToDb(expense_3);

//        System.out.println(myDBHelper.getAllSaveGoal().get(0).getTotal_amount());
//        System.out.println(myDBHelper.getSum("Income"));
//        System.out.println(myDBHelper.getSum("Expense"));

        //percentage = (total incomes - total expenses)/ amount of the first save goal
        if((myDBHelper.getSum("Income") - myDBHelper.getSum("Expense") < myDBHelper.getAllSaveGoal().get(0).getTotal_amount())){
            percentage_of_goal = (int) ((myDBHelper.getSum("Income") - myDBHelper.getSum("Expense"))
                /(myDBHelper.getAllSaveGoal().get(0).getTotal_amount())*100);}
                else {
                    percentage_of_goal = 100;
                    save_goal_frag_txt.setText("Congratulations! You have achieved your save goal!");
        }

        progressBar.setMax(100); // 100 maximum value for the progress value
        progressBar.setProgress(percentage_of_goal); // current value for the progress

        save_goal_frag_txt.setText("Your current save goal: " + percentage_of_goal + "% is done");

        // Inflate the layout for this fragment
        return view;
    }
}
