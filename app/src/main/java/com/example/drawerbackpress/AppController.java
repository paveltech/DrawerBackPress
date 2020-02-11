package com.example.drawerbackpress;

import android.app.Application;

import androidx.multidex.MultiDex;

public class AppController extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        MultiDex.install(this);

    }
}
