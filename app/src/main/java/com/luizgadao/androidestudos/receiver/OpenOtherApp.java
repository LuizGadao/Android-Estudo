package com.luizgadao.androidestudos.receiver;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.luizgadao.androidestudos.R;

public class OpenOtherApp extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_other_app);

        Button bt = (Button) findViewById( R.id.bt_open_app );
        bt.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i( "OpenOtherApp", "click-bt" );
                sendBroadcast(new Intent("OPEN_APP_TEST"));
            }
        });

    }
}
