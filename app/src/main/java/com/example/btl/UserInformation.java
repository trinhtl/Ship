package com.example.btl;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import static com.example.btl.MainActivity.MyPREFERENCES;

public class UserInformation extends AppCompatActivity {
    Button cancelBtn;
    SharedPreferences sharedpreferences;
    EditText name;
    EditText phone;
    EditText password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_information);
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        name = findViewById(R.id.name);
        phone = findViewById(R.id.phone);
        password = findViewById(R.id.password);
        name.setText(sharedpreferences.getString("name", "anonymous"));
        phone.setText(sharedpreferences.getString("phone", "00000000"));
        password.setText(sharedpreferences.getString("password", "password"));
        cancelBtn = (Button) findViewById(R.id.cancel);
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserInformation.super.onBackPressed();
            }
        });
    }
}
