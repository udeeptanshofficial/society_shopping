package com.example.rusty.society_shopping;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Spinner;

import java.util.ArrayList;

public class placeorder extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_placeorder);
        final String[] select_qualification = {"Soap","rice","chololate",
                };
        Spinner spinner = (Spinner) findViewById(R.id.spinner);

        ArrayList<placeorderstate> listVOs = new ArrayList<>();

        for (int i = 0; i < select_qualification.length; i++) {
            placeorderstate stateVO = new placeorderstate();
            stateVO.setTitle(select_qualification[i]);
            stateVO.setSelected(false);
            listVOs.add(stateVO);
        }
        MyAdapter myAdapter = new MyAdapter(this, 0, listVOs);
        spinner.setAdapter(myAdapter);
    }
}
