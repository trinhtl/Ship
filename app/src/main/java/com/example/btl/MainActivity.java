package com.example.btl;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button bt_Login, bt_dky, bt_fb;
    TextView tv_title, tv_subdky, tv_dky;
    ImageView img;
    EditText edtphone, edtpassw;
    String ten, sdt, mk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Anhxa();
        //  nhan" dky de tao tai khoan
        tv_dky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent dang_ky = new Intent(MainActivity.this, Home.class);
                startActivity(dang_ky);
            }
        });
    }

    public void  Anhxa(){
        //        ánh xạ
        bt_Login = (Button) findViewById(R.id.log_in);
        tv_title = (TextView) findViewById(R.id.nameApp);

//        bt_fb = (Button) findViewById(R.id.log_in_fb);
        tv_subdky = (TextView) findViewById(R.id.sub_dky);
        tv_dky = (TextView) findViewById(R.id.dky);
        img = (ImageView) findViewById(R.id.imageHome);
        edtphone = (EditText) findViewById(R.id.phone);
        edtpassw = (EditText) findViewById(R.id.passw);
//        bt_dky = (Button) findViewById(R.id.btdky);

    }
}
