package com.example.rusty.society_shopping;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Spinner;

public class shoporder extends AppCompatActivity {
    Spinner spn;
    ListView list_additem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shoporder);
        spn= (Spinner) findViewById(R.id.spinner);
        list_additem= (ListView) findViewById(R.id.list_additem);

    }
}
