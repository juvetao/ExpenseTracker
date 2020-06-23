package com.example.expensetracker.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.expensetracker.CreateNewSaveGoalActivity;
import com.example.expensetracker.R;
import com.example.expensetracker.VolleyNetwork;
import com.example.expensetracker.dbhelper.IncomeExpenseDBHelper;
import com.example.expensetracker.dbhelper.SaveGoalDBHelper;
import com.example.expensetracker.model.SaveGoalModel;

import org.json.JSONException;
import org.json.JSONObject;

public class SaveGoalFragment extends Fragment {
    private TextView save_goal_frag_txt;
    private ProgressBar progressBar;
    private Button create_new_save_goal_btn;
    private RequestQueue reqQueue;
//    private SaveGoalDBHelper mySaveGoalDBHelper;


    //To have the result after calculation
    private int percentage_of_goal;

    private final static String SERVER_URL = "https://reqres.in/api/";

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

        create_new_save_goal_btn = (Button) view.findViewById(R.id.save_goal_frag_btn);
        create_new_save_goal_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CreateNewSaveGoalActivity.class);
                startActivity(intent);
            }
        });

        reqQueue = Volley.newRequestQueue(getActivity());

//        mySaveGoalDBHelper = new SaveGoalDBHelper(getActivity());

        percentage_of_goal = 50;
        //Get the total amount of the first row
//        percentage_of_goal = (int) mySaveGoalDBHelper.getAllSaveGoal().get(0).getTotal_amount();


        progressBar.setMax(100); // 100 maximum value for the progress value
        progressBar.setProgress(percentage_of_goal); // current value for the progress

        save_goal_frag_txt.setText("Your current save goal: " + percentage_of_goal + "% is done");

        JsonObjectRequest myGetReq = new JsonObjectRequest(Request.Method.GET,
                SERVER_URL + "users/1",
                null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONObject data = response.getJSONObject("data");
                    System.out.println(data.toString());
                    Toast.makeText(getActivity(), data.toString(), Toast.LENGTH_SHORT).show();
                    save_goal_frag_txt.setText("Hi, " + data.getString("first_name") + " " + data.getString("last_name") + "! Your current save goal: " + percentage_of_goal + "% is done");

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

//        VolleyNetwork.getInstance(this.getActivity().getApplicationContext()).addToRequestQueue(myGetReq);
        reqQueue.add(myGetReq);


        // Inflate the layout for this fragment
        return view;
    }
}
