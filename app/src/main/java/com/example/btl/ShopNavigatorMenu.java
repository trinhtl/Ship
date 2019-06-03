package com.example.btl;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.example.btl.fragments.MapFragment;
import com.example.btl.fragments.ShopCurrentPackageFragment;
import com.example.btl.fragments.PackageFilterFragment;
import com.example.btl.fragments.PackageListFragment;
import com.example.btl.fragments.ShopProfileFragment;
import com.example.btl.fragments.UserProfileFragment;

public class ShopNavigatorMenu extends AppCompatActivity implements MapFragment.OnFragmentInteractionListener, ShopProfileFragment.OnFragmentInteractionListener, UserProfileFragment.OnFragmentInteractionListener, ShopCurrentPackageFragment.OnFragmentInteractionListener, PackageListFragment.OnFragmentInteractionListener, PackageFilterFragment.OnFragmentInteractionListener {

    private ActionBar toolbar;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.createPackageIcon:
                    toolbar.setTitle("Map");
                    fragment = new MapFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.shopActivePackageIcon:
                    toolbar.setTitle("Active Package");
                    fragment = new ShopCurrentPackageFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.userProfileIcon:
                    toolbar.setTitle("User Profile");
                    fragment = new ShopProfileFragment();
                    loadFragment(fragment);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_navigator_menu);

        toolbar = getSupportActionBar();
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.shopNavigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navigation.setSelectedItemId(R.id.createPackageIcon);
    }
    private void loadFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.shopFrame, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
