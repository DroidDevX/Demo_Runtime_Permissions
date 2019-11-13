package com.example.demo_runtime_permissions;

import android.app.Application;
import android.content.Context;

public class MyApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public Context getAppContext(){
        return super.getApplicationContext();
    }
}
