package com.example.rusty.society_shopping;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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
    final ArrayList<String> productname = new ArrayList<>();
    final ArrayList<String> productprice = new ArrayList<>();
    final ArrayList<String> selectedproductname = new ArrayList<>();
    final ArrayList<String> selectedproductprice = new ArrayList<>();
    ArrayAdapter adap;
    String shopid;
    String userid;
    ArrayList<String> ar = new ArrayList<>();
    String respons; Spinner listofdata;
    JSONObject obj;
    JSONArray jsonArray;
    SharedPreferences pref;
    int count[] = new int[20];
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_placeorder);
        list_additem= (ListView) findViewById(R.id.list_additem);
        listofdata = (Spinner) findViewById(R.id.listofdata);
        Intent intent = getIntent();
        respons = intent.getStringExtra("Society_id");
        setSpinnerAdaptor(respons);
        pref = getSharedPreferences(InitializePref.myPrefrence,this.MODE_PRIVATE);
        userid = pref.getString("Resident_name","");
        list_additem.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                    if (count[position]==1)
                    {
                        count[position]=0;
                        view.setSelected(false);
                        view.setBackgroundResource(R.color.white);
                        selectedproductname.remove(productname.get(position));
                        selectedproductprice.remove(productprice.get(position));
//                       Toast.makeText(placeorder.this, ""+selectedproductname, Toast.LENGTH_SHORT).show();
//                        Toast.makeText(placeorder.this, ""+selectedproductprice, Toast.LENGTH_SHORT).show();
                    }
                    else {
                        count[position]=1;
                        view.setSelected(true);
                        view.setBackgroundResource(R.color.colorPrimary);
                        selectedproductname.add(productname.get(position));
                        selectedproductprice.add(productprice.get(position));
//                        Toast.makeText(placeorder.this, ""+selectedproductname, Toast.LENGTH_SHORT).show();
//                        Toast.makeText(placeorder.this, ""+selectedproductprice, Toast.LENGTH_SHORT).show();
                    }
            }
        });

    }
    public void setSpinnerAdaptor(final String respons){
        StringRequest request = new StringRequest(Request.Method.POST, "https://wplanner.000webhostapp.com/user/societyShops.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("TAG", "onResponse: "+response);
                try{
                    obj = new JSONObject(response);
                    jsonArray = obj.getJSONArray("List");
                    for(int index=0;index<jsonArray.length();index++){
                        ar.add(jsonArray.getJSONObject(index).getString("name"));
                    }
                }catch(JSONException e){e.printStackTrace();}
                adap=new ArrayAdapter(placeorder.this,R.layout.support_simple_spinner_dropdown_item,ar);
                listofdata.setAdapter(adap);
                listofdata.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                      try {
                          final String name = obj.getJSONArray("List").getJSONObject(position).getString("username");
                          shopid = name;
                          StringRequest request = new StringRequest(Request.Method.POST, "https://wplanner.000webhostapp.com/user/productList.php", new Response.Listener<String>() {
                              @Override
                              public void onResponse(String response) {
                                  //this response will contain product details
                                  final ArrayList<String> shopnames = new ArrayList<>();
                                  final ArrayList<String> shopaddresss = new ArrayList<>();
                                  try {
                                      JSONObject obj1 = new JSONObject(response);
                                      JSONArray jsonArray = obj1.getJSONArray("product");
                                      productname.clear();
                                      productprice.clear();
                                      for (int i = 0; i < jsonArray.length(); i++) {
                                          JSONObject jsonObject = jsonArray.getJSONObject(i);
                                          shopnames.add(jsonObject.getString("productname"));
                                          shopaddresss.add(jsonObject.getString("price"));
                                          productname.add(jsonObject.getString("productname"));
                                          productprice.add(jsonObject.getString("price"));
                                      }

                                  }
                                  catch(JSONException e)
                                  {}
                                  ShoplistAdapter adapter = new ShoplistAdapter(placeorder.this, shopnames, shopaddresss);
                                  list_additem.setAdapter(adapter);
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
                                  map.put("shopid",name);
                                  return map;
                              }

                          };
                          RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                          queue.add(request);
                      }catch (JSONException e){}
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }

                });
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
    public void submit(View v)
    {
//        Toast.makeText(this, ""+respons, Toast.LENGTH_SHORT).show();
//        Toast.makeText(this, ""+userid, Toast.LENGTH_SHORT).show();
//        Toast.makeText(this, ""+shopid, Toast.LENGTH_SHORT).show();
        final JSONArray jsonarrayname = new JSONArray(selectedproductname);
        final JSONArray jsonarrayprice = new JSONArray(selectedproductprice);
        StringRequest stringRequest;
        stringRequest = new StringRequest(Request.Method.POST, "https://wplanner.000webhostapp.com/user/placeorder.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("DataBase Response", response);
                        Toast.makeText(placeorder.this, ""+response, Toast.LENGTH_SHORT).show();
                        Toast.makeText(placeorder.this, "Order Successful", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(placeorder.this,booking_orders.class);
                        startActivity(intent);
                        finish();
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
                params.put("societyid",respons);
                params.put("userid",userid);
                params.put("shopid",shopid);
                params.put("jsonarrayname",jsonarrayname.toString());
                params.put("jsonarrayprice",jsonarrayprice.toString());
                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(placeorder.this);
        requestQueue.add(stringRequest);

    }


    }
