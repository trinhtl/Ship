package com.example.btl;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.btl.dao.User;
import com.example.btl.fragments.Map2Fragment;
import com.example.btl.fragments.PackageFilterFragment;
import com.example.btl.fragments.PackageListFragment;
import com.example.btl.fragments.ShopCurrentPackageFragment;
import com.example.btl.fragments.ShopProfileFragment;
import com.example.btl.fragments.UserProfileFragment;
import com.example.btl.funtional.MySocket;

import org.json.JSONObject;

import io.socket.client.Socket;
import io.socket.emitter.Emitter;

public class MainActivity extends AppCompatActivity implements Map2Fragment.OnFragmentInteractionListener, UserProfileFragment.OnFragmentInteractionListener, PackageListFragment.OnFragmentInteractionListener, ShopCurrentPackageFragment.OnFragmentInteractionListener, PackageFilterFragment.OnFragmentInteractionListener, ShopProfileFragment.OnFragmentInteractionListener {
    public User userLogin;
    Button loginButton;
    Button signupButton;
    EditText phone;
    EditText password;
    SharedPreferences sharedpreferences;
    public static final String MyPREFERENCES = "MyPrefs" ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        setContentView(R.layout.activity_login);
        loginButton = (Button) findViewById(R.id.loginButton);
        phone = (EditText) findViewById(R.id.phone);
        password = (EditText) findViewById(R.id.password);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("click");

                validate(String.valueOf(phone.getText()), String.valueOf(password.getText()));
                try {
                    Thread.currentThread().join(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Loginnnnnnnnnnnn " + String.valueOf(phone.getText()) + String.valueOf(password.getText()));
                System.out.println("ready: " + userLogin);
                Intent intent = new Intent(v.getContext(), ShipperNavigatorMenu.class);
                if (userLogin != null){
                    SharedPreferences.Editor editor = sharedpreferences.edit();

                    editor.putInt("id", userLogin.getId());
                    editor.putString("phone", userLogin.getPhone());
                    editor.putString("password", userLogin.getPassword());
                    editor.putString("name", userLogin.getName());
                    editor.putString("avatar", userLogin.getAvatar());
                    editor.commit();
                    startActivity(intent);
                }
            }
        });
        signupButton = findViewById(R.id.signupButton);
        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signup = new Intent(v.getContext(), Registration.class);
                startActivity(signup);
            }
        });
    }
    public User validate(String phone, String password) {
        final Socket socket = MySocket.getInstance().socket;
        socket.connect();
        socket.emit("user/validate", phone, password);
        final User[] userLogin = new User[1];
        System.out.println("One");
        socket.on("user/validate", new Emitter.Listener() {
            @Override
            public void call(final Object... args) {
                System.out.println("go");
                System.out.println("Running....");
                JSONObject data = (JSONObject) args[0];
                if (data == null || data.length() == 0) {
                    userLogin[0] = null;
                    return;
                }
                userLogin[0] = new User(data.optInt("id"),
                        data.optString("name"),
                        data.optString("phone"),
                        data.optString("password"),
                        data.optString("avatar"));
                MainActivity.this.userLogin = userLogin[0];
                System.out.println(userLogin[0].toString());
            }
        });
        return userLogin[0];
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
