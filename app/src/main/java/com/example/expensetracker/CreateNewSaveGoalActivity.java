package com.example.expensetracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.example.expensetracker.dbhelper.ExpenseTrackerDBHelper;
import com.example.expensetracker.model.SaveGoalModel;

import java.util.ArrayList;

public class CreateNewSaveGoalActivity extends AppCompatActivity {

    private Button create_goal;
    private EditText goal_name;
    private EditText total_amount;
    private EditText length_period;
    private ExpenseTrackerDBHelper myDBHelper;
    private ListView save_goal_list;
    private ArrayAdapter adp;
    private RequestQueue reqQueue;

    private ArrayList<SaveGoalModel> save_goal_data;

    private final static String SERVER_URL = "https://reqres.in/api/";

    private SaveGoalModel save_goal_1 = new SaveGoalModel(1, "Trip", 30000.0, 12);
    private SaveGoalModel save_goal_2 = new SaveGoalModel(2, "House", 3000000.0, 60);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_save_goal);

        //initiates
        create_goal = findViewById(R.id.create_btn);
        goal_name = findViewById(R.id.edit_text_goal_name);
        total_amount = findViewById(R.id.edit_text_total_amount);
        length_period = findViewById(R.id.edit_text_length_period);
        myDBHelper = new ExpenseTrackerDBHelper(this);
        save_goal_list = findViewById(R.id.save_goal_list);

        myDBHelper.addSaveGoalToDb(save_goal_1);
        myDBHelper.addSaveGoalToDb(save_goal_2);

        updateViews();

        registerForContextMenu(save_goal_list);

        //Create a new save goal
        create_goal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //info form views
                String name = goal_name.getText().toString();
                double amount = Double.parseDouble(total_amount.getText().toString());
                int length = Integer.parseInt(length_period.getText().toString());

                //save goal object
                SaveGoalModel saveGoalToAdd = new SaveGoalModel(-1, name, amount, length);

                //add to db
                boolean status = myDBHelper.addSaveGoalToDb(saveGoalToAdd);

                //control
                Toast.makeText(CreateNewSaveGoalActivity.this, "Status:" + status, Toast.LENGTH_SHORT).show();

                updateViews();

            }


        });

    }

    //Inflate Context Menu
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.context_menu, menu);
    }

    //Control Context Menu
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo ) item.getMenuInfo();

        switch (item.getItemId()){
            case R.id.menu_item_delete:
                boolean status = myDBHelper.deleteSaveGoal((SaveGoalModel) adp.getItem(info.position));
                Toast.makeText(this, "deleted", Toast.LENGTH_SHORT).show();
                updateViews();
                break;
        }

        return super.onContextItemSelected(item);
    }

    private void updateViews(){
        adp = new ArrayAdapter<SaveGoalModel>(this, android.R.layout.simple_list_item_1, myDBHelper.getAllSaveGoal());
        save_goal_list.setAdapter(adp);
    }
}