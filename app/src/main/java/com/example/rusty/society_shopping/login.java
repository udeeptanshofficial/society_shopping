package com.example.rusty.society_shopping;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.onesignal.OneSignal;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class login extends AppCompatActivity {
    EditText et_email,et_password;
    ImageButton eye;
    Spinner spn;
    ArrayList<String> ar = new ArrayList<>();
    ArrayAdapter adap;
    String username,password,society;
    JSONObject obj;
    String respons;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        OneSignal.startInit(this).init();
        Intent intent  = getIntent();
         respons = intent.getStringExtra("Society List");
        try{
            obj = new JSONObject(respons);
            JSONArray array = obj.getJSONArray("List");
            for(int index=0;index<array.length();index++){
                JSONObject temp = array.getJSONObject(index);
                ar.add(temp.getString("societyname"));

            }

        }catch(JSONException e){}

        et_email = (EditText) findViewById(R.id.et_email);
        et_password = (EditText) findViewById(R.id.et_password);
        spn=(Spinner)findViewById(R.id.spinner);

        adap=new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,ar);
        spn.setAdapter(adap);


        eye = (ImageButton) findViewById(R.id.btn_eye);
    }
    public void login(View v){
        username = et_email.getText().toString();
        password = et_password.getText().toString();
        int position = spn.getSelectedItemPosition();
        try {
            society = obj.getJSONArray("List").getJSONObject(position).getString("societyid");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        varifyUser();
    }
    String uid;
    public void varifyUser(){
        OneSignal.startInit(this).inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification).init();

        OneSignal.idsAvailable(new OneSignal.IdsAvailableHandler() {
            @Override
            public void idsAvailable(String userId, String registrationId) {
                uid = userId;
                Log.d("debug", "registrationId:" + uid);
                if (registrationId != null)
                    Log.d("debug", "registrationId:" + registrationId);
            }
        });
        StringRequest request = new StringRequest(Request.Method.POST, "https://wplanner.000webhostapp.com/login.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //Toast.makeText(login.this, response, Toast.LENGTH_SHORT).show();
                Intent intent;
                InitializePref initPref = new InitializePref();
                if(response.equals("resident")){
                    initPref.loginResident(login.this,username);

                    intent = new Intent(login.this,booking_orders.class);
                    //intent.putExtra("Society_id",society);
                }
                else if (response.equals("shopkeeper")){
                    initPref.loginShop(login.this,username);
                    intent = new Intent(login.this,Vendor.class);
                    //intent.putExtra("Society_id",society);
                }
                else if (response.equals("admin")){
                    initPref.loginAdmin(login.this,username);
                    intent = new Intent(login.this,admin_task.class);
                    //intent.putExtra("Society_id",society);
                }
                else{
                    intent = new Intent(login.this,login.class);
                }
                intent.putExtra("Society_id",society);
                startActivity(intent);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }
        ) {
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("username", username);
                map.put("password", password);
                map.put("society",society);
                map.put("playerid",uid);
                return map;
            }

        };
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        queue.add(request);
    }

}
