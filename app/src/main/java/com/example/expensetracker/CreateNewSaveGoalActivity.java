package com.example.expensetracker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CreateNewSaveGoalActivity extends AppCompatActivity {

    private Button create_goal;
    private EditText goal_name;
    private EditText total_amount;
    private EditText length_period;
    private SaveGoalDBHelper myDBHelper;

    private ArrayList<SaveGoalModel> save_goal_data;

    private final static String SERVER_URL = "https://reqres.in/api/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_save_goal);

        //initiates
        create_goal = findViewById(R.id.create_btn);
        goal_name = findViewById(R.id.edit_text_goal_name);
        total_amount = findViewById(R.id.edit_text_total_amount);
        length_period = findViewById(R.id.edit_text_length_period);
        myDBHelper = new SaveGoalDBHelper(this);

        //Create a new save goal
        create_goal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                //info form views
//                String name = goal_name.getText().toString();
//                double amount = Double.parseDouble(total_amount.getText().toString());
//                int length = Integer.parseInt(length_period.getText().toString());
//
//                //save goal object
//                SaveGoalModel saveGoalToAdd = new SaveGoalModel(-1, name, amount, length);
//
//                //add to db
//                boolean status = myDBHelper.addSaveGoalToDb(saveGoalToAdd);
//
//                //control
//                Toast.makeText(CreateNewSaveGoalActivity.this, "Status:" + status, Toast.LENGTH_SHORT).show();

                //Post
                JSONObject postData = new JSONObject();

                try {
                    postData.put("goal name",goal_name);
                    postData.put("total amount", total_amount);
                    postData.put("period length",length_period);

                    JsonObjectRequest myPostReq = new JsonObjectRequest(Request.Method.POST, SERVER_URL + "save_goals", postData, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
//                            txt_result.setText(response.toString());
                            Toast.makeText(CreateNewSaveGoalActivity.this, response.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            error.printStackTrace();
                        }
                    });

                    VolleyNetwork.getInstance(this.getApplicationContext()).addToRequestQueue(myPostReq);

                } catch (JSONException e){
                    e.printStackTrace();
                }


            }


        });


    }
}