package com.example.btl;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.btl.dao.Package;
import com.example.btl.dao.PackageDAOImpl;

import static com.android.volley.VolleyLog.TAG;

public class PackageCreation extends AppCompatActivity {

    private TextView sendAddress;
    private TextView recieveAddress;
    private Button createBtn;
    private Button backBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_package_creation);
        createBtn = (Button) findViewById(R.id.btnCreate);
        createBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendAddress= findViewById(R.id.create_send_address);
                recieveAddress = findViewById(R.id.create_recieve_address);
                TextInputEditText shipCost = (TextInputEditText) findViewById(R.id.create_ship_cost);
                TextInputEditText advanceMoney = (TextInputEditText) findViewById(R.id.create_advance_money);
                TextInputEditText description = (TextInputEditText) findViewById(R.id.create_description);
//                Package newPackage = new Package(1,  sendAddress.getText().toString().trim(), recieveAddress.getText().toString().trim(), shipCost.getText().toString().trim(), advanceMoney.getText().toString().trim(), description.getText().toString().trim());
                Package newPackage = new Package(1, sendAddress.getText().toString().trim(), recieveAddress.getText().toString().trim(), shipCost.getText().toString().trim(), advanceMoney.getText().toString().trim(), description.getText().toString().trim());
                System.out.println(newPackage.toString());
                System.out.println(newPackage.getSendAddress().length() + ">" + newPackage.getRecieveAddress().length() + ">" + newPackage.getShipCost().length());
                if (newPackage.getSendAddress().length() > 0 && newPackage.getRecieveAddress().length() > 0 && newPackage.getShipCost().length() > 0){
                    System.out.println("create a package ");
                    createPackage(newPackage);
                    Intent intent = new Intent(v.getContext(), ShopNavigatorMenu.class);
                    startActivity(intent);
                }

            }
        });
        sendAddress = findViewById(R.id.create_send_address);
        recieveAddress = findViewById(R.id.create_recieve_address);
//        creataBtn = findViewById(R.id.btnCreate);
        backBtn = findViewById(R.id.btnBack);
        String s1 = getIntent().getStringExtra("send");
        String s2 = getIntent().getStringExtra("recieve");
        sendAddress.setText(s1);
        recieveAddress.setText(s2);

        backBtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Log.d(TAG, "ahihi");
                        PackageCreation.super.onBackPressed();
                    }
                }
        );
    }
    public void  createPackage(Package p){
        PackageDAOImpl packageDAO = new PackageDAOImpl();
        packageDAO.insert(p);
    }
}
