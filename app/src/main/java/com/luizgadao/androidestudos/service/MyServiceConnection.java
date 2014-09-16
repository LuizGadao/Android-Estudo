package com.luizgadao.androidestudos.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import com.luizgadao.androidestudos.utils.LogUtils;

public class MyServiceConnection extends Service {

    private static final String LOG_TAG = MyServiceConnection.class.getSimpleName();

    private int count = 0;
    private Boolean habilitado = true;

    private Controller controller = new Controller();

    public MyServiceConnection() {
    }

    // o retorno precisa ser um obj que extends "Binder"
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

    /* So entra no aqui se chamar o métod startService() */
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i( LOG_TAG, "onStartCommand -- startID: " + startId );
        setThread();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        LogUtils.info( LOG_TAG, "on-destroy" );
        super.onDestroy();

        habilitado = false;
    }

    public void setThread()
    {
        new Thread(){
            @Override
            public void run() {

                while ( habilitado && count < 60 )
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

    public int getCount() {
        return count;
    }

    public boolean getEnable(){ return habilitado; }

    public class Controller extends Binder
    {
        //métod que vai retornar o Serviço implementado
        public MyServiceConnection getService(){
            return MyServiceConnection.this;
        }
    }
}
