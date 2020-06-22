package com.example.expensetracker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

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
        }else {

        }
        cursor.close();
        return result;
    }

    public boolean deleteSaveGoal (SaveGoalModel saveGoalToRemove){
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(SAVE_GOAL_TABLE, COL_ID +" = " + saveGoalToRemove.getId() , null);

        return true;
    }




}
