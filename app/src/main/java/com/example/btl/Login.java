package com.example.btl;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.btl.dao.User;
import com.example.btl.dao.UserDAOImpl;
import com.example.btl.funtional.MySocket;

import org.json.JSONObject;

import io.socket.client.Socket;
import io.socket.emitter.Emitter;

public class Login extends AppCompatActivity {
    Button loginButton;
    EditText phone;
    EditText password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginButton = (Button) findViewById(R.id.loginButton);
        phone = (EditText) findViewById(R.id.phone);
        password = (EditText) findViewById(R.id.password);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("click");
                UserDAOImpl userDAO = new UserDAOImpl();
                User userLogin = validate(String.valueOf(phone.getText()), String.valueOf(password.getText()), phone, password);
                System.out.println(userLogin.toString());
                Intent intent = new Intent(v.getContext(), ShipperNavigatorMenu.class);
            }
        });
    }
    public User validate(String phone, String password, final EditText phoneText, final EditText passwordText) {
        Socket socket = MySocket.getInstance().socket;
        socket.connect();
        socket.emit("user/validate", phone, password);
        final User[] userLogin = new User[1];
        socket.on("user/validate", new Emitter.Listener() {
            @Override
            public void call(final Object... args) {
                runOnUiThread(new Runnable() {
                    public void run() {
                        System.out.println("Running....");
                        JSONObject data = (JSONObject) args[0];
                        userLogin[0] = new User(data.optInt("id"),
                                data.optString("name"),
                                data.optString("phone"),
                                data.optString("password"),
                                data.optString("avatar"));
                        phoneText.notify();
                        passwordText.notify();
                    }
                });
            }
        });
        return userLogin[0];
    }
}
