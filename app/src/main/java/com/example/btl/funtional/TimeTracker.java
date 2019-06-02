package com.example.btl.funtional;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class TimeTracker {
    public static Date getCurrentDate(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        java.util.Date date = new java.util.Date();
        java.util.Date current = new java.util.Date(dateFormat.format(date));
        return null;
    }
}
