package com.example.rusty.society_shopping;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;

public class pendingorder extends AppCompatActivity implements View.OnClickListener {

    EditText e1,e2,e3,e4,e5;
    CheckBox check;
    Button btn;
    ListView list_additem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pendingorder);

        e1=(EditText)findViewById(R.id.shop);
        e2=(EditText)findViewById(R.id.customer);
        e3=(EditText)findViewById(R.id.total);
        e4=(EditText)findViewById(R.id.address);
        e5=(EditText)findViewById(R.id.pick);
        list_additem= (ListView) findViewById(R.id.list_additem);


        check=(CheckBox)findViewById(R.id.check);
        check.setOnClickListener(this);

        btn=(Button)findViewById(R.id.btn);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

    }
}
