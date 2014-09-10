package com.luizgadao.androidestudos.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.luizgadao.androidestudos.ui.view.MyCustomView;

public class MyReceiver1 extends BroadcastReceiver {
    public MyReceiver1() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText( context, "MY RECEIVER TEST 1", Toast.LENGTH_SHORT ).show();

        //RECEIVE OPEN NEW ACTIVITY IT IS NECESSARY OPEN SET FLAG NEW TASK
        Intent intent1 = new Intent( context, MyCustomView.class );
        intent1.setFlags( Intent.FLAG_ACTIVITY_NEW_TASK );
        context.startActivity( intent1 );

    }
}
