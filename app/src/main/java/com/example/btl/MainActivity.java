package com.example.btl;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.btl.fragments.CreatePackageFragment;
import com.example.btl.fragments.CurrentPackageFragment;
import com.example.btl.fragments.PackageFilterFragment;
import com.example.btl.fragments.PackageListFragment;
import com.example.btl.fragments.UserProfileFragment;

public class MainActivity extends AppCompatActivity implements UserProfileFragment.OnFragmentInteractionListener, PackageListFragment.OnFragmentInteractionListener, CreatePackageFragment.OnFragmentInteractionListener, CurrentPackageFragment.OnFragmentInteractionListener, PackageFilterFragment.OnFragmentInteractionListener {
//    Button bt_Login, bt_dky, bt_fb;
//    TextView tv_title, tv_subdky, tv_dky;
//    ImageView img;
//    EditText edtphone, edtpassw;
//    String ten, sdt, mk;
    Button bt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        bt = findViewById(R.id.button);



//        Anhxa();
//        //  nhan" dky de tao tai khoan
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent btn = new Intent(MainActivity.this, ShipperNavigatorMenu.class);
                startActivity(btn);
            }
        });
    }

//    public void  Anhxa(){
//        //        ánh xạ
//        bt_Login = (Button) findViewById(R.id.log_in);
//        tv_title = (TextView) findViewById(R.id.nameApp);
//
////        bt_fb = (Button) findViewById(R.id.log_in_fb);
//        tv_subdky = (TextView) findViewById(R.id.sub_dky);
//        tv_dky = (TextView) findViewById(R.id.dky);
//        img = (ImageView) findViewById(R.id.imageHome);
//        edtphone = (EditText) findViewById(R.id.phone);
//        edtpassw = (EditText) findViewById(R.id.passw);
////        bt_dky = (Button) findViewById(R.id.btdky);
//
//    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }
//    private  List<Test> getList(){
//
//    }
}
