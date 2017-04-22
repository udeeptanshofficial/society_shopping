package com.example.rusty.society_shopping;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        EditText et_email,et_password;
        Button frgtPassword,login;
        ImageButton eye;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        et_email = (EditText) findViewById(R.id.et_email);
        et_password = (EditText) findViewById(R.id.et_password);
        frgtPassword=(Button) findViewById(R.id.btn_forgotpassword);
        login=(Button) findViewById(R.id.btn_login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent   intent = new Intent(getApplicationContext(), booking_orders.class);
                startActivity(intent);
                finish();
            }




        });

        eye = (ImageButton) findViewById(R.id.btn_eye);



    }
}
