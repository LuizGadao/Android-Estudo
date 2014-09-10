package com.luizgadao.androidestudos.notification;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.luizgadao.androidestudos.R;

public class CreateNotification extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_notification);

        Button btNotification = (Button) findViewById( R.id.bt_notification );
        btNotification.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i( "NOTIFICATION", "launch" );
                NotificationTest.notify( getBaseContext(), "My TEST NOTIFICATION", 1 );
            }
        });
    }



}
