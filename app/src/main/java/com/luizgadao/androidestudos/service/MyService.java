package com.luizgadao.androidestudos.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service implements Runnable {

    private static final String LOG_TAG = MyService.class.getSimpleName();
    private Boolean active;
    private Thread myThread;

    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();

        this.active = true;
        this.myThread = new Thread( this );
        myThread.start();

        Log.i( LOG_TAG, "on-create-service" );
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Log.i( LOG_TAG, "onStartCommand-service startID: " + startId );
        // se o android fechar esse serviço, não é para ele reinicia-lo o memso.
        //return START_NOT_STICKY;

        //se o android fechar esse serviço, quando não tiver recurso disponivel ele deve inicia-lo novamente. So que o valor da intent é nulo.
        //return START_STICKY;

        //se o android fechar esse serviço, quando tiver recurso disponivel ele deve inicia-lo novamente. Valor da Intent é o mesmo enviado para reiniciar o serviço.
        return START_NOT_STICKY;

        //return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Log.i( LOG_TAG, "onDestroy" );
        active = false;
        super.onDestroy();
    }

    @Override
    public void run()
    {
        int count = 1;
        while( active && count < 100  )
        {
            doSomething();

            Log.i( "MyService", "count: " + count );
            count++;
        }

        stopSelf();
    }

    private void doSomething() {

        try {
            Thread.sleep( 1000 );
        }
        catch ( InterruptedException e )
        {
            e.printStackTrace();
        }
    }

    public Boolean getActive() {
        return active;
    }
}
