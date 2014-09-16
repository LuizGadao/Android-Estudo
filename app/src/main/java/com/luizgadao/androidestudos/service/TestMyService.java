package com.luizgadao.androidestudos.service;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.luizgadao.androidestudos.R;

public class TestMyService extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_service1);


        final Intent intentService = new Intent("MY_SERVICE");

        Button btStart = (Button) findViewById( R.id.bt_start_service );
        Button btStop = (Button) findViewById( R.id.bt_stop_service );

        btStart.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startService( intentService );
            }
        });

        btStop.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i( "TestService1", "stop service? " + stopService( intentService ) );
            }
        });

    }



}
