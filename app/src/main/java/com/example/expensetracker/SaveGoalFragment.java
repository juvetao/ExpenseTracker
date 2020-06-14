package com.example.expensetracker;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;


import androidx.fragment.app.Fragment;

public class SaveGoalFragment extends Fragment {

    private ProgressBar progressBar;
    private Button create_new_save_goal_btn;
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


        create_new_save_goal_btn = (Button) view.findViewById(R.id.save_goal_frag_btn);
        create_new_save_goal_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CreateNewSaveGoalActivity.class);
                startActivity(intent);
            }
        });


        // Inflate the layout for this fragment
        return view;
    }

}
