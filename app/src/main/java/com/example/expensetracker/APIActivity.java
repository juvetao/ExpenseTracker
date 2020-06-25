package com.example.expensetracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.os.Bundle;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.expensetracker.model.UserModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;

public class APIActivity extends AppCompatActivity implements View.OnClickListener {
    private ListView users_list;
    private Button btn_get, btn_post, btn_put, btn_delete;
    private TextView txt_result;
    private ArrayAdapter adp;
    private ArrayList<UserModel> userList;

    private final static String SERVER_URL = "https://reqres.in/api/";
    private final static String SERVER_URL_DEL = "https://jsonplaceholder.typicode.com/posts/1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_api);

        users_list = findViewById(R.id.users_list);
        btn_get = findViewById(R.id.btn_get);
        btn_post = findViewById(R.id.btn_post);
        btn_put = findViewById(R.id.btn_put);
        btn_delete = findViewById(R.id.btn_delete);
        txt_result = findViewById(R.id.txt_result);
        userList = new ArrayList();

        //make ListView users_list connect with the context menu
        registerForContextMenu(users_list);

        updateView();

        btn_get.setOnClickListener(this);
        btn_put.setOnClickListener(this);
        btn_post.setOnClickListener(this);
        btn_delete.setOnClickListener(this);

    }

    private void updateView() {
        adp = new ArrayAdapter(this, android.R.layout.simple_list_item_1, userList);
        users_list.setAdapter(adp);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){

            //GET
            case R.id.btn_get:

                JsonObjectRequest myGetReq = new JsonObjectRequest(Request.Method.GET, SERVER_URL + "users?page=2", null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        //clear first to avoid the list duplicated
                        userList.clear();

                        try {
                            JSONArray dataObject = response.getJSONArray("data");
                            for(int i=0; i < dataObject.length();i++){
                                JSONObject userObject = dataObject.getJSONObject(i);

                                UserModel tempUser = new UserModel(userObject.getInt("id"),
                                        userObject.getString("email"),
                                        userObject.getString("first_name"),
                                        userObject.getString("last_name"),
                                        userObject.getString("avatar"));


                                userList.add(tempUser);

                                updateView();
                            }

                        }catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(APIActivity.this, "Failed" + error.toString(), Toast.LENGTH_SHORT).show();
                        error.printStackTrace();
                    }
                });

                VolleyNetwork.getInstance(this.getApplicationContext()).addToRequestQueue(myGetReq);

                break;

                //POST
            case R.id.btn_post:
                Toast.makeText(this, "POST", Toast.LENGTH_SHORT).show();

                JSONObject postData = new JSONObject();

                try {
                    postData.put("name","Cheng");
                    postData.put("course", "Android");
                    postData.put("id","10");

                    JsonObjectRequest myPostReq = new JsonObjectRequest(Request.Method.POST, SERVER_URL + "users", postData, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            txt_result.setText(response.toString());
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

                break;

                //PUT
            case R.id.btn_put:
                Toast.makeText(this, "PUT", Toast.LENGTH_SHORT).show();

                JSONObject putData = new JSONObject();

                try {
                    putData.put("name", "Cheng Tao");
                    putData.put("job", "developer");

                    JsonObjectRequest myPutReq = new JsonObjectRequest(Request.Method.PUT, SERVER_URL + "users/2", putData, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            txt_result.setText(response.toString());
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            error.printStackTrace();
                        }
                    });

                    VolleyNetwork.getInstance(this.getApplicationContext()).addToRequestQueue(myPutReq);
                }
                catch (JSONException e){
                    e.printStackTrace();
                }
                break;

                //DELETE
            case R.id.btn_delete:
                Toast.makeText(this, "DELETE", Toast.LENGTH_SHORT).show();

                JsonObjectRequest myDeleteReq = new JsonObjectRequest(Request.Method.DELETE, SERVER_URL_DEL, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        txt_result.setText(response.toString());
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        txt_result.setText(error.toString());
                        error.printStackTrace();
                    }
                });

                VolleyNetwork.getInstance(this.getApplicationContext()).addToRequestQueue(myDeleteReq);
                break;
        }
    }

    //context menu

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.context_menu_user_api, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        final AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()){
            case R.id.context_item_view_details:
                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);

                //Inflate the AlertDialog with user_dialog
                LayoutInflater inflater = getLayoutInflater();
                View dialogView = inflater.inflate(R.layout.user_dialog, null);

                dialogBuilder.setView(dialogView);

                //Declare
                final ImageView dialog_imageview;
                final TextView dialog_txt_id;
                final TextView dialog_txt_fullname;
                final TextView dialog_txt_email;

                //initiate
                dialog_imageview = dialogView.findViewById(R.id.dialog_imageview);
                dialog_txt_id = dialogView.findViewById(R.id.dialog_txt_id);
                dialog_txt_fullname = dialogView.findViewById(R.id.dialog_txt_fullname);
                dialog_txt_email = dialogView.findViewById(R.id.dialog_txt_email);

                //Setters
                Picasso.get().load(userList.get(info.position).getPicture()).into(dialog_imageview);
                dialog_txt_id.setText(String.valueOf(userList.get(info.position).getId()));
                dialog_txt_fullname.setText(userList.get(info.position).getFirstname() + " " + userList.get(info.position).getLastname());
                dialog_txt_email.setText(userList.get(info.position).getEmail());

                final AlertDialog addDialog = dialogBuilder.create();
                addDialog.show();


        }
        return super.onContextItemSelected(item);
    }
}