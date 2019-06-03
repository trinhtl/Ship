package com.example.btl;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.btl.dao.UserDAOImpl;
import com.example.btl.fragments.ShopCurrentPackageFragment;
import com.example.btl.fragments.PackageFilterFragment;
import com.example.btl.fragments.PackageListFragment;
import com.example.btl.fragments.ShopProfileFragment;
import com.example.btl.fragments.UserProfileFragment;

import io.socket.client.Socket;

public class MainActivity extends AppCompatActivity implements UserProfileFragment.OnFragmentInteractionListener, PackageListFragment.OnFragmentInteractionListener, ShopCurrentPackageFragment.OnFragmentInteractionListener, PackageFilterFragment.OnFragmentInteractionListener, ShopProfileFragment.OnFragmentInteractionListener {

    Socket socket;

    Button bt;
    UserDAOImpl userDAOImpl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        bt = findViewById(R.id.button);

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent btn = new Intent(MainActivity.this, ShipperNavigatorMenu.class);
                startActivity(btn);

            }
        });
    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
