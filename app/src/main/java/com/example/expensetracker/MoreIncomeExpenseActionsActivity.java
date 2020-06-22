package com.example.expensetracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class MoreIncomeExpenseActionsActivity extends AppCompatActivity{

    private IncomeExpenseDBHelper myDBHelper;
    private ArrayAdapter adp;
    private ListView income_expense_list;
    private IncomeExpenseDBHelper myDbHelper;

    private ArrayList<IncomeExpenseModel> incomes_expenses_data;

    //private final static String SERVER_URL = "https://reqres.in/api/";


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
//                Toast.makeText(this, "Add", Toast.LENGTH_SHORT).show();
                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);

                //Inflate View for alertdialog
                LayoutInflater inflater = getLayoutInflater();
                View dialogView = inflater.inflate(R.layout.add_income_expense_dialog, null);

                dialogBuilder.setView(dialogView);

                //Declare
                final EditText income_expense_edit_txt, category_edit_txt, amount_edit_txt, date_edit_txt;
                final RadioGroup radio_group;
                Button add_btn;

                //Initialize
                income_expense_edit_txt = dialogView.findViewById(R.id.income_expense_edit_txt);
                category_edit_txt = dialogView.findViewById(R.id.category_edit_txt);
                amount_edit_txt = dialogView.findViewById(R.id.amount_edit_txt);
                date_edit_txt = dialogView.findViewById(R.id.date_edit_txt);
                add_btn = dialogView.findViewById(R.id.add_btn);
                incomes_expenses_data = new ArrayList<>();

                updateViews();

                final IncomeExpenseModel tempIncomeExpense = (IncomeExpenseModel) adp.getItem(info.position);

                //Setters
                income_expense_edit_txt.setText(tempIncomeExpense.getIncomeExpense().toString());
                category_edit_txt.setText(tempIncomeExpense.getCategory());
                amount_edit_txt.setText((int) tempIncomeExpense.getAmount());
                date_edit_txt.setText(tempIncomeExpense.getDate());

                final AlertDialog addDialog = dialogBuilder.create();
                addDialog.show();

                add_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        tempIncomeExpense.setIncomeExpense(income_expense_edit_txt.getText().toString());
                        tempIncomeExpense.setCategory(category_edit_txt.getText().toString());
                        tempIncomeExpense.setAmount(Double.parseDouble(amount_edit_txt.getText().toString()));
                        tempIncomeExpense.setDate(Integer.parseInt(date_edit_txt.getText().toString()));

                        myDBHelper.addIncomeExpenseToDb(tempIncomeExpense);

                        Toast.makeText(MoreIncomeExpenseActionsActivity.this, "Added", Toast.LENGTH_SHORT).show();
                        updateViews();

                        addDialog.hide();
                    }
                });

            case R.id.search_menu:
                Toast.makeText(this, "Search", Toast.LENGTH_SHORT).show();
                break;
            case R.id.exit_menu:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void updateViews(){
        adp = new ArrayAdapter<IncomeExpenseModel>(this, android.R.layout.simple_list_item_1, myDBHelper.getAllIncomeExpenses());
        income_expense_list.setAdapter(adp);
    }

}