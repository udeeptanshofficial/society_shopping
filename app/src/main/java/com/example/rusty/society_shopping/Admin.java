package com.example.rusty.society_shopping;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class Admin extends AppCompatActivity {
    TextView t1;
    ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        t1=(TextView)findViewById(R.id.adm);
        img=(ImageView)findViewById(R.id.img);

    }
}
