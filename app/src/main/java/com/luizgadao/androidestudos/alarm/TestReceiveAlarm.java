package com.luizgadao.androidestudos.alarm;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.TextView;

import com.luizgadao.androidestudos.R;
import com.luizgadao.androidestudos.utils.LogUtils;

import java.util.Calendar;

public class TestReceiveAlarm extends ActionBarActivity {

    private static final String LOG = TestReceiveAlarm.class.getSimpleName();
    private int seconds = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_receiver_alarm);

        TextView textView = (TextView) findViewById( R.id.text_alarm );
        textView.setText( "Alarme agenda para daqui a " + seconds + " segundos." );

        agendar( seconds );
    }

    private void agendar(int seconds) {
        Intent intent = new Intent("RUN_ALARM");
        PendingIntent pendingIntent = PendingIntent.getBroadcast( this, 0, intent, 0 );

        //executar o alarme apartir de daqui
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis( System.currentTimeMillis() );
        calendar.add( Calendar.SECOND, seconds );

        //agendar alarm
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        long time = calendar.getTimeInMillis();
        alarmManager.set( AlarmManager.RTC_WAKEUP, time, pendingIntent );

        LogUtils.info( LOG, "Alarme agendado para daqui a: " + seconds + " segundos." );
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        // cancela alarme.
        Intent intent = new Intent("RUN_ALARM");
        PendingIntent pendingIntent = PendingIntent.getBroadcast( this, 0, intent, 0 );

        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager.cancel( pendingIntent );
    }
}
