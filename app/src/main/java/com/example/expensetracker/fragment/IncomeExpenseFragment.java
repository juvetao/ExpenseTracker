package com.example.expensetracker.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.example.expensetracker.MainActivity;
import com.example.expensetracker.R;
import com.example.expensetracker.dbhelper.ExpenseTrackerDBHelper;
import com.example.expensetracker.model.IncomeExpenseModel;

public class IncomeExpenseFragment extends Fragment {

    private ExpenseTrackerDBHelper myDBHelper;
    private ArrayAdapter adp;
    private ListView listView;

    private double totalIncome;
    private double totalExpense;



    public IncomeExpenseFragment() {
    }

    public double getTotalIncome() {
        return totalIncome;
    }

    public double getTotalExpense() {
        return totalExpense;
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

        updateViews();
        totalIncome = updateTotalValues("Income");
        totalExpense = updateTotalValues("Expense");

        registerForContextMenu(listView);

        return view;
    }

    public void updateViews(){
        adp = new ArrayAdapter<IncomeExpenseModel>(getActivity(), android.R.layout.simple_list_item_1, myDBHelper.getAllIncomeExpenses());
        listView.setAdapter(adp);
    }

    public double updateTotalValues(String incomeOrExpense){
        //System.out.println("Total Incomes: " + myDBHelper.getSum("Income"));
        double total = myDBHelper.getSum(incomeOrExpense);
        System.out.println("Total " + incomeOrExpense + ": " + total);
        return total;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_main, menu);
//        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void onPause() {
        super.onPause();
        updateViews();
    }

    //Control Action Bar Menu
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.add_menu:
                AlertDialog.Builder dialogBuilder_add = new AlertDialog.Builder(getActivity());

                //Inflate View for AlertDialog
                LayoutInflater inflater_add = getLayoutInflater();
                View dialogView_add = inflater_add.inflate(R.layout.add_income_expense_dialog, null);

                dialogBuilder_add.setView(dialogView_add);

                //Declare
                final EditText income_expense_edit_txt, category_edit_txt, amount_edit_txt, date_edit_txt;
                Button add_btn;

                //Initialize
                income_expense_edit_txt = dialogView_add.findViewById(R.id.income_expense_edit_txt);
                category_edit_txt = dialogView_add.findViewById(R.id.category_edit_txt);
                amount_edit_txt = dialogView_add.findViewById(R.id.amount_edit_txt);
                date_edit_txt = dialogView_add.findViewById(R.id.date_edit_txt);
                add_btn = dialogView_add.findViewById(R.id.add_btn);

                updateViews();
                totalIncome = updateTotalValues("Income");
                totalExpense = updateTotalValues("Expense");

                final AlertDialog addDialog = dialogBuilder_add.create();
                addDialog.show();

                add_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String income_expense = income_expense_edit_txt.getText().toString();
                        String category = category_edit_txt.getText().toString();
                        double amount = Double.parseDouble(amount_edit_txt.getText().toString());
                        int date = Integer.parseInt(date_edit_txt.getText().toString());

                        IncomeExpenseModel tempIncomeExpense = new IncomeExpenseModel(-1, income_expense, category, amount, date);

                        myDBHelper.addIncomeExpenseToDb(tempIncomeExpense);

                        Toast.makeText(getActivity(), "Added", Toast.LENGTH_SHORT).show();
                        updateViews();
                        totalIncome = updateTotalValues("Income");
                        totalExpense = updateTotalValues("Expense");


                        addDialog.hide();
                        addDialog.dismiss();
                    }
                });

                updateViews();
                return true;
//                break;

            case R.id.search_menu:
                AlertDialog.Builder dialogBuilder_search = new AlertDialog.Builder(getActivity());

                //Inflate View for AlertDialog
                LayoutInflater inflater_search = getLayoutInflater();
                View dialogView_search = inflater_search.inflate(R.layout.search_income_expense_dialog, null);

                dialogBuilder_search.setView(dialogView_search);

                //Declare
                final AutoCompleteTextView auto_com_income_expense;
                final ListView search_result_list;
                final EditText search_edit_txt;
                final Button search_btn;

                //Initialize
                search_edit_txt = dialogView_search.findViewById(R.id.search_edit_txt);
                search_result_list = dialogView_search.findViewById(R.id.search_list_view);
                search_btn = dialogView_search.findViewById(R.id.search_btn);

                final AlertDialog searchDialog = dialogBuilder_search.create();
                searchDialog.show();

                search_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String categoryForSearch = search_edit_txt.getText().toString();

                        Toast.makeText(getActivity(), "Searched", Toast.LENGTH_SHORT).show();

                        adp = new ArrayAdapter<IncomeExpenseModel>(v.getContext(), android.R.layout.simple_list_item_1, myDBHelper.getIncomeExpenseByCategory(categoryForSearch));
                        search_result_list.setAdapter(adp);
                    }
                });

                Toast.makeText(getActivity(), "Search", Toast.LENGTH_SHORT).show();
                return true;
//                break;
            case R.id.exit_menu:
                //EXIT from app
//                Intent intent = new Intent(Intent.ACTION_MAIN);
//                intent.addCategory(Intent.CATEGORY_HOME);
//                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                startActivity(intent);

                //Back to the login activity
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
//                break;
                return true;
        }
//        return super.onOptionsItemSelected(item);
        return false;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        //super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getActivity().getMenuInflater();
        inflater.inflate(R.menu.context_menu, menu);
    }

    //Control Context Menu
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo ) item.getMenuInfo();

        switch (item.getItemId()){
            case R.id.menu_item_delete:
                boolean status = myDBHelper.deleteIncomeExpense((IncomeExpenseModel) adp.getItem(info.position));
                Toast.makeText(getActivity(), "deleted", Toast.LENGTH_SHORT).show();
                updateViews();
                totalIncome = updateTotalValues("Income");
                totalExpense = updateTotalValues("Expense");
                break;
        }

        return super.onContextItemSelected(item);
    }

    Fragment fragment = new Fragment();

}



