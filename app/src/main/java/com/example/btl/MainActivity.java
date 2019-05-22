package com.example.btl;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.btl.dao.UserDAOImpl;
import com.example.btl.fragments.CreatePackageFragment;
import com.example.btl.fragments.CurrentPackageFragment;
import com.example.btl.fragments.PackageFilterFragment;
import com.example.btl.fragments.PackageListFragment;
import com.example.btl.fragments.UserProfileFragment;

import java.net.URISyntaxException;

import io.socket.client.IO;
import io.socket.client.Socket;

public class MainActivity extends AppCompatActivity implements UserProfileFragment.OnFragmentInteractionListener, PackageListFragment.OnFragmentInteractionListener, CreatePackageFragment.OnFragmentInteractionListener, CurrentPackageFragment.OnFragmentInteractionListener, PackageFilterFragment.OnFragmentInteractionListener {
//    Button bt_Login, bt_dky, bt_fb;
//    TextView tv_title, tv_subdky, tv_dky;
//    ImageView img;
//    EditText edtphone, edtpassw;
//    String ten, sdt, mk;
    Socket socket;

    Button bt;
    UserDAOImpl userDAOImpl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        bt = findViewById(R.id.button);
//        userDAOImpl = new UserDAOImpl();
//        userDAOImpl.getAll();
//        getData();
//        System.out.println("socket-0----------------------------------------------------");
//        socket = MySocket.getInstance().socket;
//        socket.connect();
//        socket.send("Hi");
//        socket.emit("user/list");
//
//        Emitter on = socket.on("connection", new Emitter.Listener() {
//            @Override
//            public void call(Object... args) {
//                Log.v("Response", args[0].toString());
//                System.out.println("Log: " + args[0].toString());
//            }
//        });
//        Anhxa();
////        //  nhan" dky de tao tai khoan
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent btn = new Intent(MainActivity.this, ShipperNavigatorMenu.class);
                startActivity(btn);

//                socket.emit("user/add", "shipper", "12345678", "Phạm Try A", "try@gmail.com",null);
//                socket.emit("user/update/role", 3, "shop");
            }
        });
//        socket.on("user/list", new Emitter.Listener() {
//            @Override
//            public void call(final Object... args) {
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        String data = (String) args[0].toString();
//                        // get the extra data from the fired event and display a toast
////                        Toast.makeText(ChatBoxActivity.this,data,Toast.LENGTH_SHORT).show();
//                        System.out.println(data);
//                    }
//                });
//            }
//        });
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
    private void getData(){
        Log.v("Message", "Get data");
        System.out.println("Get data");
        try {
            Socket socket = IO.socket("http://localhost:3000");
            System.out.println(socket.toString());
            socket.emit("hello", "hi");
//            socket.on("connection", new Emitter.Listener() {
//                @Override
//                public void call(Object... args) {
//                    Log.v("Response", args[0].toString());
//                    System.out.println("Log: " + args[0].toString());
//                }
//            });
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
