package com.example.btl;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;

import com.example.btl.fragments.CreatePackageFragment;
import com.example.btl.fragments.CurrentPackageFragment;
import com.example.btl.fragments.PackageFilterFragment;
import com.example.btl.fragments.PackageListFragment;
import com.example.btl.fragments.UserProfileFragment;

import java.net.URISyntaxException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

public class ShipperNavigatorMenu extends AppCompatActivity implements CurrentPackageFragment.OnFragmentInteractionListener, PackageListFragment.OnFragmentInteractionListener, CreatePackageFragment.OnFragmentInteractionListener, UserProfileFragment.OnFragmentInteractionListener, PackageFilterFragment.OnFragmentInteractionListener {

    private ActionBar toolbar;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.activePackageIcon:
                    toolbar.setTitle("Active Package");
                    fragment = new CurrentPackageFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.listPackageIcon:
                    toolbar.setTitle("Package List");
                    fragment = new PackageListFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.filtPackageIcon:
                    toolbar.setTitle("Filt Package");
                    fragment = new PackageFilterFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.userProfileIcon:
                    toolbar.setTitle("User Profile");
                    fragment = new UserProfileFragment();
                    loadFragment(fragment);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shipper_navigator_menu);

        toolbar = getSupportActionBar();
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.shipperNavigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }
    private void loadFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.shipperFrame, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
    private List<Item> getListData(){
        Log.i("getList", "get data");
        List<Item> list = new ArrayList<Item>();
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        Item first = new Item(1, "a", "Jack", "50k", "20k", new Date(dateFormat.format(date)), "181 Xuân Thủy, Cầu Giấy", "18 Ba Đình", "Cần gấp");
        list.add(first);
        Item second = new Item(2, "a", "Mary", "50k", "20k", new Date(dateFormat.format(date)), "181 Xuân Thủy, Cầu Giấy", "18 Ba Đình", "Cần gấp");
        list.add(second);
        return  list;
    }

}
