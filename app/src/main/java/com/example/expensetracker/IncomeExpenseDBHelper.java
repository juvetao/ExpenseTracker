package com.example.expensetracker;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.sql.Date;
import java.text.DateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class IncomeExpenseDBHelper extends SQLiteOpenHelper {
    public static final String INCOME_EXPENSE_TABLE = "INCOME_EXPENSE_TABLE";
    public static final String COL_ID = "ID";
    public static final String COL_INCOME_EXPENSE = "INCOME_EXPENSE";
    public static final String COL_CATEGORY = "CATEGORY";
    public static final String COL_AMOUNT = "AMOUNT";
    public static final String COL_DATE = "DATE";


    public IncomeExpenseDBHelper(@Nullable Context context) {
        super(context, "incomeExpense.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String create_table_query = "CREATE TABLE " + INCOME_EXPENSE_TABLE + " ( "+ COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT , " + COL_INCOME_EXPENSE + " TEXT, " + COL_CATEGORY + " TEXT, " + COL_AMOUNT + " NUMERIC, " + COL_DATE + " DATE) ";
        db.execSQL(create_table_query);

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
        cv.put(COL_DATE, incomeExpenseToAdd.getDate().toString());

        long insertStatus = db.insert(INCOME_EXPENSE_TABLE, null, cv);

        db.close();
        if(insertStatus == -1){
            return false;
        }else {
            return true;
        }
    }
}
