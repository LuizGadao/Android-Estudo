package com.luizgadao.androidestudos.alarm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.luizgadao.androidestudos.utils.LogUtils;

public class ReceiverAlarm extends BroadcastReceiver {
    private static final String LOG = ReceiverAlarm.class.getSimpleName();

    public ReceiverAlarm() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        LogUtils.info(LOG, "on-receiver");
        Toast.makeText(context, "RECEIVER-ALARM", Toast.LENGTH_SHORT).show();
    }
}
