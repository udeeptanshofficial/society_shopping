package com.example.rusty.society_shopping;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

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

import java.util.HashMap;
import java.util.Map;

public class login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        EditText et_email,et_password;
        Button frgtPassword,login;
        ImageButton eye;
        Spinner spn;
        ArrayList ar;
        ArrayAdapter adap;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        OneSignal.startInit(this).init();

        et_email = (EditText) findViewById(R.id.et_email);
        et_password = (EditText) findViewById(R.id.et_password);

        frgtPassword = (Button) findViewById(R.id.btn_forgotpassword);

        frgtPassword=(Button) findViewById(R.id.btn_forgotpassword);
        spn=(Spinner)findViewById(R.id.spinner);
        ar=new ArrayList();
        ar.add("");
        ar.add("");
        ar.add("");
        ar.add("");
        ar.add("");
        adap=new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,ar);
        spn.setAdapter(adap);

        login=(Button) findViewById(R.id.btn_login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                varifyUser();

            }




        });
        eye = (ImageButton) findViewById(R.id.btn_eye);

    }
    public void varifyUser(){
        StringRequest request = new StringRequest(Request.Method.POST, "https://wplanner.000webhostapp.com/login.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try{
                    JSONArray array = new JSONArray(response);
                    /*String status = array.getString(1);
                    String role = array.getString(2);
                    InitializePref initPref = new InitializePref();
                    if(status.equals("success") && role.equals("agent")){
                        Intent intent = new Intent(Login.this,LPAgent.class);
                        initPref.loginAgent(Login.this,array.getString(0));
                        startActivity(intent);

                    }
                    else if(status.equals("success") && role.equals("student")){

                        Intent intent = new Intent(Login.this,MainDrawer.class);
                        initPref.loginStudent(Login.this,array.getString(0));
                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(Login.this,"Invalid Credentials....",Toast.LENGTH_LONG).show();
                    }
                    loading.dismiss();*/
                }catch(JSONException e){}






            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }
        ) {
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                /*map.put("username", username1);
                map.put("password", password1);
                Log.d("TAG", "getParams: "+uid);
                map.put("oneSignalId",uid);*/
                return map;
            }

        };
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        queue.add(request);
    }

}
