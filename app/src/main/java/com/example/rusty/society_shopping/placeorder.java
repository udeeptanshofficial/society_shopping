package com.example.rusty.society_shopping;

import android.content.Intent;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class placeorder extends AppCompatActivity {
    ListView list_additem;

    ArrayAdapter adap;
    ArrayList<String> ar = new ArrayList<>();
    String respons; Spinner listofdata;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_placeorder);
        list_additem= (ListView) findViewById(R.id.list_additem);
        listofdata = (Spinner) findViewById(R.id.listofdata);
        Intent intent = getIntent();
        listofdata.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                listofdata.getSelectedItemPosition();
                // Add tey catch code for getting shopid and society id
                //addproducts(shopid,societyid);
            }
        });
        respons = intent.getStringExtra("Society_id");
        setSpinnerAdaptor(respons);

        //init();

    }
    public void setSpinnerAdaptor(final String respons){
        StringRequest request = new StringRequest(Request.Method.POST, "https://wplanner.000webhostapp.com/user/societyShops.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("TAG", "onResponse: "+response);
                try{
                    JSONObject obj = new JSONObject(response);
                    JSONArray jsonArray = obj.getJSONArray("List");
                    for(int index=0;index<jsonArray.length();index++){
                        ar.add(jsonArray.getJSONObject(index).getString("name"));
                    }
                }catch(JSONException e){e.printStackTrace();}
                adap=new ArrayAdapter(placeorder.this,R.layout.support_simple_spinner_dropdown_item,ar);
                listofdata.setAdapter(adap);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }
        ) {
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();

                map.put("societyid",respons);
                return map;
            }

        };
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        queue.add(request);

    }
    public  void addproducts(final String shopid, final String societyid)
    {

        final ArrayList<String> shopnames = new ArrayList<>();
        final ArrayList<String> shopaddresss = new ArrayList<>();
        StringRequest stringRequest;
        stringRequest = new StringRequest(Request.Method.POST, "",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("DataBase Response", response);
                        if (response.equals("fail") == false) {
                            try {
                                JSONArray jsonArray = new JSONArray(response);
                                for(int i=0;i<jsonArray.length();i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    shopnames.add(jsonObject.getString("productname"));
                                    shopaddresss.add(jsonObject.getString("price"));
                                }
                                ShoplistAdapter adapter = new ShoplistAdapter(placeorder.this,shopnames,shopaddresss);
                                list_additem.setAdapter(adapter);

                            } catch (JSONException e) {
                                e.printStackTrace();

                            }




                        }
                        else {
//                            loading.dismiss();
                        }
                    }

                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(placeorder.this, "No Internet Connection", Toast.LENGTH_SHORT).show();
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

        RequestQueue requestQueue = Volley.newRequestQueue(placeorder.this);
        requestQueue.add(stringRequest);
    }


    }




//table work
   /* public void init() {
        TableLayout stk = (TableLayout) findViewById(R.id.tablelistofdata);
        TableRow tableRow1 = new TableRow(this);
        TextView tv_serialno = new TextView(this);
      tv_serialno.setTextColor(Color.BLACK);
        tableRow1.addView(tv_serialno);


        TextView tv_list = new TextView(this);
        tv_list.setTextColor(Color.BLACK);
        tableRow1.addView(tv_list);

        TextView tvprice = new TextView(this);
        tvprice.setTextColor(Color.BLACK);
       tableRow1.addView(tvprice);

        TextView tv_check = new TextView(this);
        tv_check.setTextColor(Color.BLACK);
        tableRow1.addView(tv_check);

        stk.addView(tableRow1);

        for (int i = 0; i < 25; i++) {
            TableRow tablerow = new TableRow(this);
            TextView tv_serialno1 = new TextView(this);
            tv_serialno1 .setText("" + i);
            tv_serialno1.setTextColor(Color.BLACK);
            tv_serialno1.setGravity(Gravity.CENTER);
           tableRow1.addView(tv_serialno1);


            TextView tv_list1 = new TextView(this);
            tv_list1.setText(" " + i);
            tv_list1.setTextColor(Color.BLACK);
            tv_list1.setGravity(Gravity.CENTER);
          tablerow.addView(tv_list1);

            TextView  tvprice1 = new TextView(this);
            tvprice1.setText("Rs." + i);
            tvprice1.setTextColor(Color.BLACK);
            tvprice1.setGravity(Gravity.CENTER);
              tablerow.addView( tvprice);

            TextView tv_check1 = new TextView(this);
            tv_check1.setText("" + i );
            tv_check1.setTextColor(Color.BLACK);
            tv_check1.setGravity(Gravity.CENTER);
            tablerow.addView(tv_check1);
            stk.addView(tablerow);
        }

    }*/

