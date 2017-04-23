package com.example.rusty.society_shopping;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;

public class placeorder extends AppCompatActivity {
    ListView list_additem;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_placeorder);
        Spinner listofdata = (Spinner) findViewById(R.id.listofdata);
        list_additem= (ListView) findViewById(R.id.list_additem);


    }

    //spinner
    public void loadlistofitem()
    {

    }

    }
