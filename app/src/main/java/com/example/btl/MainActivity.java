package com.example.btl;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

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
//        List<Test> data =

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
//    private  List<Test> getList(){
//
//    }
}
