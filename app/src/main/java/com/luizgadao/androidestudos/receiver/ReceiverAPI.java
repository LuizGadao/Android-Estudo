package com.luizgadao.androidestudos.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class ReceiverAPI extends BroadcastReceiver {
    public ReceiverAPI() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText( context, "Receiver definition by API JAVA", Toast.LENGTH_LONG ).show();
    }
}
