package com.example.rusty.society_shopping;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class MainActivity extends AppCompatActivity {
    int SPLASH_TIME_OUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSocietyList();

    }
    String response;
    public void getSocietyList(){

        StringRequest stringRequest = new StringRequest(Request.Method.GET, "https://wplanner.000webhostapp.com/loadSocietyList.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        response=s;
                        new Handler().postDelayed(new Runnable() {

                            @Override
                            public void run() {
                                // This method will be executed once the timer is over
                                // Start your app main activity
                                Intent login=new Intent(getApplicationContext(), login.class);
                                login.putExtra("Society List",response);
                                startActivity(login);

                                finish();
                            }
                        }, SPLASH_TIME_OUT);



                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {



                    }
                }){


        };

        //Creating a Request Queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        //Adding request to the queue
        requestQueue.add(stringRequest);

    }
    }

