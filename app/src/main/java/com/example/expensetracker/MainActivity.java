package com.example.expensetracker;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.expensetracker.dbhelper.ExpenseTrackerDBHelper;
import com.example.expensetracker.model.IncomeExpenseModel;
import com.example.expensetracker.model.SaveGoalModel;

public class MainActivity extends AppCompatActivity {

    public final static String SHARED_PREFS = "SHAREDPREF";
    public final static String EDITVIEW_NAME = "Username";
    public final static String EDITVIEW_PASS = "Password";

    String user, pass;

    private EditText name_input, password_input;
    private Button login_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name_input = findViewById(R.id.name_input);
        password_input = findViewById(R.id.password_input);
        login_btn = findViewById(R.id.login_btn);


        login_btn.setEnabled(false);
        name_input.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.equals("")){
                    login_btn.setEnabled(false);
                }else {
                    login_btn.setEnabled(true);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TabActivity.class);

                user = name_input.getEditableText().toString();
                String valid_username = "Cheng";

                pass = password_input.getEditableText().toString();
                String valid_password = "juve1210";

                if(user.equals(valid_username) && pass.equals(valid_password)){
                    //intent.putExtra("Username", user);
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        saveUserInfo();
//        savePassword();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadUserInfo();
//        loadPassword();

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TabActivity.class);

                user = name_input.getEditableText().toString();
                String valid_username = "Cheng";

                pass = password_input.getEditableText().toString();
                String valid_password = "juve1210";

                if(user.equals(valid_username) && pass.equals(valid_password)){
                    intent.putExtra("Username", user);
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void saveUserInfo(){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(EDITVIEW_NAME, user);
        editor.putString(EDITVIEW_PASS, pass);

        editor.apply();
    }

    public void loadUserInfo(){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        user = sharedPreferences.getString(EDITVIEW_NAME, "");
        pass = sharedPreferences.getString(EDITVIEW_PASS, "");
    }

//    public void savePassword(){
//        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS_PASS, MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//
//        editor.putString(EDITVIEW_PASS, pass);
//
//        editor.apply();
//        Toast.makeText(this, EDITVIEW_PASS+"is saved", Toast.LENGTH_SHORT).show();
//    }
//
//
//    public void loadPassword(){
//        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS_PASS, MODE_PRIVATE);
//        pass = sharedPreferences.getString(EDITVIEW_PASS, "");
//    }

}
