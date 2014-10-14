package com.luizgadao.androidestudos.service;

import android.app.IntentService;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import com.luizgadao.androidestudos.utils.LogUtils;


public class MyIntentService extends IntentService {

    private static final String LOG_TAG = IntentService.class.getSimpleName();
    private int count = 0;
    private Boolean enable;
    private Controller controller = new Controller();

    public MyIntentService() {
        super("MyIntentService");
        count = 0;
        enable = true;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return controller;
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);

        LogUtils.info( LOG_TAG, "on-start: " + startId );
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        LogUtils.info( LOG_TAG, "on-start-command: " + startId );
        intent.putExtra("id", startId);

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    protected void onHandleIntent(Intent intent)
    {
        int id = intent.getExtras().getInt( "id" );

        while( count < 10 && enable )
        {
            try {
                Thread.sleep( 1000 );
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            count++;
            LogUtils.info( LOG_TAG, "startID: " + id + " count: " + count );
        }

        count = 0;
    }

    public void dissableService(){
        this.enable = false;
        count = 0;
    }

    public Boolean getEnable(){
        return enable;
    }

    public int getCount(){ return count; }

    public class Controller extends Binder
    {
        public MyIntentService getMyIntentService(){ return MyIntentService.this; }
    }


}
