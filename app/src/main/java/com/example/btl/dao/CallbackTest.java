package com.example.btl.dao;

import android.view.View;

import java.util.List;

interface CallBack {
    void methodToCallBack(int[] a);
}

public class CallbackTest {
    public void methodToCallBack(List<Item> itemList) {
        System.out.println("test itemlist cb" + itemList);
    }

    public List<Item> callback2(View view, List<Item> itemList) {
        System.out.println("test itemlist cb" + itemList);
        return itemList;
    }
}
