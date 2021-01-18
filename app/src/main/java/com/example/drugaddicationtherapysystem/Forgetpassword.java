package com.example.drugaddicationtherapysystem;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class Forgetpassword extends AppCompatActivity {
           EditText Email;
           Button Submitbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgetpassword);
        Email=(EditText) findViewById(R.id.emailid);
        Submitbutton=findViewById(R.id.btn_submit);
        Submitbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });


    }
}