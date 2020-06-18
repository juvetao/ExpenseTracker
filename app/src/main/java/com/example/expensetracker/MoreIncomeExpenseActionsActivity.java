package com.example.expensetracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.Toast;

public class MoreIncomeExpenseActionsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_actions_for_income_expense);
    }

    //Inflate the menu file to show the main menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    //Control the menu
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo ) item.getMenuInfo();
        switch (item.getItemId()){
            case R.id.add_menu:
                Toast.makeText(this, "Add", Toast.LENGTH_SHORT).show();
                //AlertDialog.Builder dialogBuilder = new AlertDialog.Builder();
            case R.id.search_menu:
                Toast.makeText(this, "Search", Toast.LENGTH_SHORT).show();
                break;
            case R.id.exit_menu:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}