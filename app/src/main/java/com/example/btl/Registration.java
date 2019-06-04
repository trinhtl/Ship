package com.example.btl;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Registration extends AppCompatActivity {
    Button back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Registration.super.onBackPressed();
            }
        });
    }

}

