package com.example.geeknews.classes;

import android.app.Application;

public class MyApplication extends Application {
    public static String tokenApplication ="";
    @Override
    public void onCreate() {
        super.onCreate();
        // do the ACRA init here
    }
}
