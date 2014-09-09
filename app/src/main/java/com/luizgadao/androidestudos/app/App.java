package com.luizgadao.androidestudos.app;

import android.app.Application;

/**
 * Created by luizcarlos on 09/09/14.
 */
public class App extends Application
{

    private static Application app;

    public static Application getApp()
    {
        return  app;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        App.app = this;
    }
}
