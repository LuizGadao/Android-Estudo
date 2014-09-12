package com.luizgadao.androidestudos.service;

import android.app.IntentService;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import com.luizgadao.androidestudos.utils.LogUtils;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class MyIntentService extends IntentService {

    private static final String LOG_TAG = IntentService.class.getSimpleName();
    private int count = 0;
    private Boolean enable;
    private Boolean enableAll;
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

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    protected void onHandleIntent(Intent intent)
    {
        while( count < 5 && enable )
        {
            try {
                Thread.sleep( 1000 );
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            count++;
            LogUtils.info( LOG_TAG, "count: " + count );
        }

        count = 0;
    }

    public void dissableService(){
        this.enable = false;
        count = 0;
    }

    public int getCount(){ return count; }

    public class Controller extends Binder
    {
        public MyIntentService getMyIntentService(){ return MyIntentService.this; }
    }


}
