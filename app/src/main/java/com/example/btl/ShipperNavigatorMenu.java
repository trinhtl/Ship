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

import com.example.btl.fragments.PackageFilterFragment;
import com.example.btl.fragments.PackageListFragment;
import com.example.btl.fragments.ShipperCurrentPackageFragment;
import com.example.btl.fragments.ShopCurrentPackageFragment;
import com.example.btl.fragments.ShopProfileFragment;
import com.example.btl.fragments.UserProfileFragment;

public class ShipperNavigatorMenu extends AppCompatActivity implements ShipperCurrentPackageFragment.OnFragmentInteractionListener, ShopProfileFragment.OnFragmentInteractionListener, ShopCurrentPackageFragment.OnFragmentInteractionListener, PackageListFragment.OnFragmentInteractionListener, UserProfileFragment.OnFragmentInteractionListener, PackageFilterFragment.OnFragmentInteractionListener {

    private ActionBar toolbar;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.activePackageIcon:
                    toolbar.setTitle("Active Package");
                    fragment = new ShipperCurrentPackageFragment();
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
        navigation.setSelectedItemId(R.id.activePackageIcon);

    }
    private void loadFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.shipperFrame, fragment);
//        FrameLayout shipperFrameLayout = (FrameLayout) findViewById(R.id.shipperFrame);
        transaction.addToBackStack(null);
        transaction.commit();

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

}
