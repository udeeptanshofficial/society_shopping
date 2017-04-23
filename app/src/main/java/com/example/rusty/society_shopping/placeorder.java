package com.example.rusty.society_shopping;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;

public class placeorder extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_placeorder);
        Spinner listofdata = (Spinner) findViewById(R.id.listofdata);
        listofdata.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);
        init();

    }

    //spinner
    public void loadlistofitem()
    {

    }




//table work
    public void init() {
        TableLayout stk = (TableLayout) findViewById(R.id.tablelistofdata);
        TableRow tableRow1 = new TableRow(this);
        TextView tv_serialno = new TextView(this);
      tv_serialno.setTextColor(Color.WHITE);
        tableRow1.addView(tv_serialno);


        TextView tv_list = new TextView(this);
        tv_list.setTextColor(Color.WHITE);
        tableRow1.addView(tv_list);

        TextView tvprice = new TextView(this);
        tvprice.setTextColor(Color.WHITE);
       tableRow1.addView(tvprice);

        TextView tv_check = new TextView(this);
        tv_check.setTextColor(Color.WHITE);
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
            tv_list1.setText("Product " + i);
            tv_list1.setTextColor(Color.BLACK);
            tv_list1.setGravity(Gravity.CENTER);
          tablerow.addView(tv_list1);

            TextView  tvprice1 = new TextView(this);
            tvprice1.setText("Rs." + i);
            tvprice1.setTextColor(Color.WHITE);
            tvprice1.setGravity(Gravity.CENTER);
              tablerow.addView( tvprice);

            TextView tv_check1 = new TextView(this);
            tv_check1.setText("" + i );
            tv_check1.setTextColor(Color.WHITE);
            tv_check1.setGravity(Gravity.CENTER);
            tablerow.addView(tv_check1);
            stk.addView(tablerow);
        }

    }

}
