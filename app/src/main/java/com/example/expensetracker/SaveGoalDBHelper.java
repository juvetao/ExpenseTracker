package com.example.expensetracker;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class SaveGoalDBHelper extends SQLiteOpenHelper {
    public static final String SAVE_GOAL_TABLE = "SAVE_GOAL_TABLE";
    public static final String COL_ID = "ID";
    public static final String COL_GOAL_NAME = "GOAL_NAME";
    public static final String COL_TOTAL_AMOUNT = "TOTAL_AMOUNT";
    public static final String COL_PERIOD_LENGTH = "PERIOD_LENGTH";


    public SaveGoalDBHelper(@Nullable Context context) {
        super(context, "saveGoal.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String create_table_query = "CREATE TABLE " + SAVE_GOAL_TABLE + " ( "+ COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT , " + COL_GOAL_NAME + " TEXT, " + COL_TOTAL_AMOUNT + " NUMERIC, " + COL_PERIOD_LENGTH + " INTEGER) ";

        db.execSQL(create_table_query);
    }

    //Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        // Drop older table if existed
//        db.execSQL("DROP TABLE IF EXISTS " + SAVE_GOAL_TABLE);
//
//        //Create tables again
//        onCreate(db);

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
}
