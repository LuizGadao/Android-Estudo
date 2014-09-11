package com.luizgadao.androidestudos.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import com.luizgadao.androidestudos.receiver.CountListener;

public class MyServiceConnection extends Service implements CountListener {

    private static final String LOG_TAG = MyServiceConnection.class.getSimpleName();

    private int count = 0;
    private Boolean habilitado = true;

    private Controller controller = new Controller();

    public MyServiceConnection() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return controller;
    }

    @Override
    public void onCreate() {

        super.onCreate();
        Log.i( LOG_TAG, "on-create" );
        setThread();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i( LOG_TAG, "onStartCommand -- startID: " + startId );
        setThread();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        habilitado = false;
    }

    public void setThread()
    {
        new Thread(){
            @Override
            public void run() {

                while ( habilitado && count < 100 )
                {
                    try {
                        Thread.sleep( 1000 );
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    count++;

                    Log.i( LOG_TAG, " - count: " + count );
                }

            }
        }.start();
    }

    @Override
    public int getCount() {
        return count;
    }

    public class Controller extends Binder
    {
        public CountListener getCountListener(){
            return MyServiceConnection.this;
        }
    }
}