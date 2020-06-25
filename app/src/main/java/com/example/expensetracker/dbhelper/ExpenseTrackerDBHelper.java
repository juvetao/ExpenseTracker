package com.example.expensetracker.dbhelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.expensetracker.model.IncomeExpenseModel;
import com.example.expensetracker.model.SaveGoalModel;

import java.util.ArrayList;
import java.util.List;

public class ExpenseTrackerDBHelper extends SQLiteOpenHelper {
    public static final String INCOME_EXPENSE_TABLE = "INCOME_EXPENSE_TABLE";
    public static final String COL_INCOME_EXPENSE_ID = "ID";
    public static final String COL_INCOME_EXPENSE = "INCOME_EXPENSE";
    public static final String COL_CATEGORY = "CATEGORY";
    public static final String COL_AMOUNT = "AMOUNT";
    public static final String COL_DATE = "DATE";

    public static final String SAVE_GOAL_TABLE = "SAVE_GOAL_TABLE";
    public static final String COL_SAVE_GOAL_ID = "ID";
    public static final String COL_GOAL_NAME = "GOAL_NAME";
    public static final String COL_TOTAL_AMOUNT = "TOTAL_AMOUNT";
    public static final String COL_PERIOD_LENGTH = "PERIOD_LENGTH";


    public ExpenseTrackerDBHelper(@Nullable Context context) {
        super(context, "expensetracker.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String create_table_query_income_expense = "CREATE TABLE " + INCOME_EXPENSE_TABLE + " ( "+ COL_INCOME_EXPENSE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT , " + COL_INCOME_EXPENSE + " TEXT, " + COL_CATEGORY + " TEXT, " + COL_AMOUNT + " NUMERIC, " + COL_DATE + " NUMERIC) ";
        db.execSQL(create_table_query_income_expense);

        String create_table_query_save_goal = "CREATE TABLE " + SAVE_GOAL_TABLE + " ( "+ COL_SAVE_GOAL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT , " + COL_GOAL_NAME + " TEXT, " + COL_TOTAL_AMOUNT + " NUMERIC, " + COL_PERIOD_LENGTH + " INTEGER) ";
        db.execSQL(create_table_query_save_goal);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean addIncomeExpenseToDb (IncomeExpenseModel incomeExpenseToAdd){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(COL_INCOME_EXPENSE, incomeExpenseToAdd.getIncomeExpense().toString());
        cv.put(COL_CATEGORY, incomeExpenseToAdd.getCategory());
        cv.put(COL_AMOUNT, incomeExpenseToAdd.getAmount());
        cv.put(COL_DATE, incomeExpenseToAdd.getDate());

        long insertStatus = db.insert(INCOME_EXPENSE_TABLE, null, cv);

        db.close();
        if(insertStatus == -1){
            return false;
        }else {
            return true;
        }
    }

    public ArrayList<IncomeExpenseModel> getAllIncomeExpenses(){
        ArrayList<IncomeExpenseModel> result = new ArrayList<IncomeExpenseModel>();
        SQLiteDatabase db = this.getReadableDatabase();

        String get_all_income_expense_query = "SELECT * FROM " + INCOME_EXPENSE_TABLE;

        Cursor cursor = db.rawQuery(get_all_income_expense_query, null);


        if(cursor.moveToFirst()){
            do{
                int income_expense_id = cursor.getInt(0);
                String income_or_expense = cursor.getString(1);
                String category = cursor.getString(2);
                double amount = cursor.getDouble(3);
                int date = cursor.getInt(4);

                IncomeExpenseModel incomeExpenseModel = new IncomeExpenseModel(income_expense_id, income_or_expense, category, amount, date);
                result.add(incomeExpenseModel);

            }while (cursor.moveToNext());
        }

        cursor.close();
        return result;
    }

    public boolean deleteIncomeExpense (IncomeExpenseModel incomeExpenseRemove){
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(INCOME_EXPENSE_TABLE,  COL_INCOME_EXPENSE_ID + " = " + incomeExpenseRemove.getId(), null);

        return true;
    }

//    public void updateIncomeExpense (IncomeExpenseModel incomeExpenseUpdate){
//        SQLiteDatabase db = this.getWritableDatabase();
//
//        ContentValues cv = new ContentValues();
//        cv.put(COL_INCOME_EXPENSE_ID, incomeExpenseUpdate.getId());
//        cv.put(COL_INCOME_EXPENSE, incomeExpenseUpdate.getIncomeExpense());
//        cv.put(COL_CATEGORY, incomeExpenseUpdate.getCategory());
//        cv.put(COL_AMOUNT, incomeExpenseUpdate.getAmount());
//        cv.put(COL_DATE, incomeExpenseUpdate.getDate());
//
//        db.update(INCOME_EXPENSE_TABLE, cv, COL_INCOME_EXPENSE_ID + " = " + incomeExpenseUpdate.getId(), null);
//    }

    //Search for income expense data by category
    public ArrayList<IncomeExpenseModel> getIncomeExpenseByCategory (String searchCategory){
        ArrayList<IncomeExpenseModel> result = new ArrayList<IncomeExpenseModel>();
        SQLiteDatabase db = this.getReadableDatabase();

        String get_income_expense_by_category = "SELECT * FROM " + INCOME_EXPENSE_TABLE + " WHERE " + COL_CATEGORY + " LIKE '%"+searchCategory+"%'";
        Cursor cursor = null;
        String[] columns= {COL_INCOME_EXPENSE_ID, COL_INCOME_EXPENSE, COL_CATEGORY, COL_AMOUNT, COL_DATE};

        try{
            cursor = db.rawQuery(get_income_expense_by_category, null);

            if(cursor != null){
                cursor.moveToFirst();{
                do{
                    int income_expense_id = cursor.getInt(0);
                    String income_or_expense = cursor.getString(1);
                    String category = cursor.getString(2);
                    double amount = cursor.getDouble(3);
                    int date = cursor.getInt(4);

                    IncomeExpenseModel incomeExpenseModel = new IncomeExpenseModel(income_expense_id, income_or_expense, category, amount, date);
                    result.add(incomeExpenseModel);

                    }while (cursor.moveToNext());
                    cursor.close();
                    return result;
                }
            }else {
                return null;
                }
            } catch (Exception e) {
            e.printStackTrace();
        }
        cursor.close();
        return result;
        }

    //Calculate Sum of expenses
    public double getSum (String incomeOrExpense){
        SQLiteDatabase db = this.getReadableDatabase();

        String get_sum = "SELECT SUM ("+ COL_AMOUNT + ") as Sum FROM " + INCOME_EXPENSE_TABLE + " WHERE " + COL_INCOME_EXPENSE + "='" + incomeOrExpense + "'";
        Cursor cursor = db.rawQuery(get_sum, null);
        if(cursor.moveToFirst()) {
           return cursor.getDouble(cursor.getColumnIndex("Sum"));
        }
        return 0.0;
    }

    public boolean addSaveGoalToDb (SaveGoalModel saveGoalToAdd){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(COL_GOAL_NAME, saveGoalToAdd.getGoal_name());
        cv.put(COL_TOTAL_AMOUNT, saveGoalToAdd.getTotal_amount());
        cv.put(COL_PERIOD_LENGTH, saveGoalToAdd.getPeriod_length());

        long insertStatus = db.insert(SAVE_GOAL_TABLE, null, cv);


        db.close();
        if(insertStatus == -1){
            return false;
        }else {
            return true;
        }
    }

    public List<SaveGoalModel> getAllSaveGoal(){
        List<SaveGoalModel> result = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String get_all_save_goal_query = "SELECT * FROM " + SAVE_GOAL_TABLE;

        Cursor cursor = db.rawQuery(get_all_save_goal_query, null);

        if(cursor.moveToFirst()){
            do{
                int save_goal_id = cursor.getInt(0);
                String save_goal_name = cursor.getString(1);
                double save_goal_total_amount = cursor.getDouble(2);
                int save_goal_period = cursor.getInt(3);

                SaveGoalModel tempSaveGoal = new SaveGoalModel(save_goal_id, save_goal_name, save_goal_total_amount, save_goal_period);

                result.add(tempSaveGoal);
            }while (cursor.moveToNext());
        }else{
            return null;
        }
        cursor.close();
        return result;
    }

    public boolean deleteSaveGoal (SaveGoalModel saveGoalToRemove){
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(SAVE_GOAL_TABLE, COL_SAVE_GOAL_ID +" = " + saveGoalToRemove.getId() , null);

        return true;
    }
}
