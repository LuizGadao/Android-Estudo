package com.luizgadao.androidestudos.alarm;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.TextView;

import com.luizgadao.androidestudos.R;

import java.util.Calendar;

public class TestReceiveAlarmRepeat extends ActionBarActivity {

    private long repeatSecods = 8 * 1000;
    private int seconds = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_receive_alarm_repeat);

        TextView textView = (TextView) findViewById(R.id.text_alarm);
        textView.setText( "alarme começa em: " + seconds + "segudos e repete em: " + repeatSecods + " segundos." );
        setupAlarm( seconds, repeatSecods );
    }

    private void setupAlarm(int seconds, long repeatSecods) {
        Intent intent = new Intent("RUN_ALARM");
        PendingIntent pendingIntent = PendingIntent.getBroadcast( this, 0, intent, 0 );

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis( System.currentTimeMillis() );
        calendar.add( Calendar.SECOND, seconds );

        long time = calendar.getTimeInMillis();

        AlarmManager alarmManager = (AlarmManager) getSystemService( ALARM_SERVICE );
        alarmManager.setRepeating( AlarmManager.RTC_WAKEUP, time, repeatSecods, pendingIntent );
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Intent intent = new Intent("RUN_ALARM");
        PendingIntent pendingIntent = PendingIntent.getBroadcast( this, 0, intent, 0 );

        AlarmManager alarmManager = (AlarmManager) getSystemService( ALARM_SERVICE );
        alarmManager.cancel( pendingIntent );
    }
}
