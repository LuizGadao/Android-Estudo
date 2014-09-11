package com.luizgadao.androidestudos.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class TestBootComplete extends BroadcastReceiver {
    public TestBootComplete() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        //Toast.makeText( context, "My receiver BOOT COMPLETE", Toast.LENGTH_LONG ).show();
        intent = new Intent( "SERVICE_1" );
        context.startService( intent );
    }
}
