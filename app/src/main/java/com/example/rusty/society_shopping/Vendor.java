package com.example.rusty.society_shopping;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Vendor extends AppCompatActivity implements View.OnClickListener {
    Button order,product;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendor);
        order=(Button)findViewById(R.id.b1);
        order.setOnClickListener(this);
        product=(Button)findViewById(R.id.b2);
        product.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        
    }
}
