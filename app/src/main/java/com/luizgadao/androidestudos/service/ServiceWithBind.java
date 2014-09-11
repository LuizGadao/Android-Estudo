package com.luizgadao.androidestudos.service;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.luizgadao.androidestudos.R;
import com.luizgadao.androidestudos.receiver.CountListener;

public class ServiceWithBind extends ActionBarActivity implements ServiceConnection{

    private ServiceConnection connection;
    private CountListener controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_with_bind);

        connection = this;

        Button btStart = (Button) findViewById( R.id.bt_start_service );
        Button btStop = (Button) findViewById( R.id.bt_stop_service );
        Button btCount = (Button) findViewById( R.id.bt_count );

        final Intent intentService = new Intent("SERVICE_CONNECTION");

        btStart.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Context.BIND_AUTO_CREATE;
                bindService( intentService, connection, Context.BIND_AUTO_CREATE);
            }
        });

        btStop.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                unbindService( connection );
            }
        });

        btCount.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText( getBaseContext(), "Count: " + controller.getCount(), Toast.LENGTH_SHORT ).show();
            }
        });
    }

    @Override
    public void onServiceConnected(ComponentName componentName, IBinder service) {
        MyServiceConnection.Controller cl  = (MyServiceConnection.Controller) service;
        controller = cl.getCountListener();
        //controller.getCount();
    }

    @Override
    public void onServiceDisconnected(ComponentName componentName) {

    }

}
