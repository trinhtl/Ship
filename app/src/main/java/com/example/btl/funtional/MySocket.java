package com.example.btl.funtional;

import java.net.URISyntaxException;

import io.socket.client.IO;
import io.socket.client.Socket;

public class MySocket {
    private static MySocket INSTANCE = null;
    public Socket socket;
    private MySocket(){
        try {
            socket = IO.socket("http://192.168.0.103:3000");
            System.out.println(socket.toString());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
    public static MySocket getInstance(){
        if (INSTANCE == null){
            INSTANCE = new MySocket();
        }
        return INSTANCE;
    }

}
