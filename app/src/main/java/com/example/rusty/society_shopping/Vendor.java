package com.example.rusty.society_shopping;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Vendor extends AppCompatActivity implements View.OnClickListener {
    Button order,product;
    String societyid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendor);
        order=(Button)findViewById(R.id.b1);
        order.setOnClickListener(this);
        product=(Button)findViewById(R.id.b2);
        product.setOnClickListener(this);
        Intent intent = getIntent();
        societyid = intent.getStringExtra("Society_id");
    }

    @Override
    public void onClick(View v)
    {
        Intent shoporder=new Intent(getApplicationContext(),shoporder.class);
        shoporder.putExtra("Society_id",societyid);
        startActivity(shoporder);
        
    }
}
