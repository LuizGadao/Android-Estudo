package com.luizgadao.androidestudos.service;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Toast;

import com.luizgadao.androidestudos.R;
import com.luizgadao.androidestudos.utils.LogUtils;

public class TestIntentService extends ActionBarActivity implements ServiceConnection {

    private static final String LOG_TAG = TestIntentService.class.getSimpleName();
    private Intent intentService;
    private MyIntentService.Controller controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_service_intent);

        intentService = new Intent( "SERVICE_INTENT" );

        //ser tiver algum servico rodando em background conecta a ele.
        bindService( intentService, this, BIND_AUTO_CREATE );
    }

    public void startService( View view )
    {
        startService(intentService);
        bindService( intentService, this, BIND_AUTO_CREATE );
    }

    public void stopService( View view )
    {
        if ( this.controller != null & this.controller.getMyIntentService().getEnable()) {
            unbindService(this);
            stopService(intentService);
            //hack for stop service.
            this.controller.getMyIntentService().dissableService();
        }
    }

    public void getCount( View view )
    {
        if ( controller != null )
            Toast.makeText(getBaseContext(), "Count: " + controller.getMyIntentService().getCount(), Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(getBaseContext(), "Ainda não foi iniciado o serviço.", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        LogUtils.info( LOG_TAG, "onServiceConnected" );
        this.controller = (MyIntentService.Controller) iBinder;
    }

    @Override
    public void onServiceDisconnected(ComponentName componentName)
    {
        LogUtils.info( LOG_TAG, "onServiceDisconnected" );
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        /*é necessário usar o bind-service para desconectar do serviço. Mas, o serviço ainda continua executando.*/
        if ( controller != null & controller.getMyIntentService().getEnable() )
            unbindService( this );
    }
}
