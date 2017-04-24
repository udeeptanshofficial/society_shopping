package com.example.rusty.society_shopping;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class shoporder extends AppCompatActivity {
    Spinner spn;
    ListView list_additem;
    String societyid,shopid;
    SharedPreferences pref;
    ArrayList<String> ar = new ArrayList<>();
    ArrayAdapter adap;
    JSONObject obj;
    JSONArray jsonArray;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shoporder);
        Intent intent = getIntent();
        societyid = intent.getStringExtra("Society_id");
        spn= (Spinner) findViewById(R.id.spinner);
        list_additem= (ListView) findViewById(R.id.list_additem);
        pref = getSharedPreferences(InitializePref.myPrefrence,this.MODE_PRIVATE);
        shopid = pref.getString("Shop_name","");
        addSpinnerData();


    }
    public void addSpinnerData(){
        StringRequest stringRequest;
        stringRequest = new StringRequest(Request.Method.POST, "https://wplanner.000webhostapp.com/shop/loadorders.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            obj = new JSONObject(response);
                            jsonArray = obj.getJSONArray("List");
                            for(int index=0;index<jsonArray.length();index++){
                                ar.add(jsonArray.getJSONObject(index).getString("orderid"));
                            }
                        }catch(JSONException e){e.printStackTrace();}
                        adap=new ArrayAdapter(shoporder.this,R.layout.support_simple_spinner_dropdown_item,ar);
                        spn.setAdapter(adap);
                    }

                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //Toast.makeText(placeorder.this, "No Internet Connection", Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("societyid",societyid);
                params.put("shopid",shopid);

                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(shoporder.this);
        requestQueue.add(stringRequest);

    }
}
