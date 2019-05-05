package com.example.btl;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import java.net.URISyntaxException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

public class MainActivity extends AppCompatActivity {
//    Button bt_Login, bt_dky, bt_fb;
//    TextView tv_title, tv_subdky, tv_dky;
//    ImageView img;
//    EditText edtphone, edtpassw;
//    String ten, sdt, mk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_package_list);
        List<Item> pakageListData = getListData();
        ListView pakageList = (ListView) findViewById(R.id.list);
        pakageList.setAdapter(new ListPackageAdapter(pakageListData, this));


//        Anhxa();
//        //  nhan" dky de tao tai khoan
//        tv_dky.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent dang_ky = new Intent(MainActivity.this, Home.class);
//                startActivity(dang_ky);
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
    private void getData(){
        try {
            Socket socket = IO.socket("http://localhost:3000");
            socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {
                @Override
                public void call(Object... args) {
                    Log.e("Response", args[0].toString());
                }
            });
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
//    private  List<Test> getList(){
//
//    }
}
