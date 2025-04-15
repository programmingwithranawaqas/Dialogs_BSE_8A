package com.example.dialogs_bse_8a;

import android.app.Application;

import java.util.ArrayList;

public class DataClass extends Application {
    public static ArrayList<Passenger> data;

    @Override
    public void onCreate() {
        super.onCreate();
        data = new ArrayList<>();

    }
}
