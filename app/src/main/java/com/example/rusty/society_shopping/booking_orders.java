package com.example.rusty.society_shopping;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class booking_orders extends AppCompatActivity
{


    @Override

    protected void onCreate(Bundle savedInstanceState)
    {
        Button placeorder,pendingorder,myorderlist;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_orders);

       placeorder=(Button) findViewById(R.id.placeorder);
        placeorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent placeoder=new Intent(getApplicationContext(),placeorder.class);
                startActivity(placeoder);
                finish();
            }
        });
      pendingorder=(Button) findViewById(R.id.pendingorder);
        pendingorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pendingorder=new Intent(getApplicationContext(),pendingorder.class);
                startActivity(pendingorder);
                finish();

            }
        });
        myorderlist= (Button) findViewById(R.id.myorderlist);
        myorderlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myorders=new Intent(getApplicationContext(),myorders.class);
                startActivity(myorders);
                finish();

            }
        });


//        myorderlist//society name  spinner
//        Spinner spinner = (Spinner) findViewById(R.id.spinner);
//
//        // Spinner click listener
//        spinner.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);
//
//        // Spinner Drop down elements
//        List<String> categories = new ArrayList<String>();
//        categories.add("REDLEAF SERVICED APARTMENT");
//        categories.add("NEW MODERN APARTMENT ");
//        categories.add("The Lotus APARTMENT ");
//        categories.add("Anara Homes & Villa");
//        categories.add("Evergreen APARTMENT ");
//        categories.add("Mayas Nest ");
//
//        // Creating adapter for spinner
//        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);
//
//        // Drop down layout style - list view with radio button
//        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//
//        // attaching data adapter to spinner
//        spinner.setAdapter(dataAdapter);
//    }
//
//    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//        // On selecting a spinner item
//        String item = parent.getItemAtPosition(position).toString();
//
//        // Showing selected spinner item
//        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
//    }
//    public void onNothingSelected(AdapterView<?> arg0) {
//        // TODO Auto-generated method stub
   }




}
