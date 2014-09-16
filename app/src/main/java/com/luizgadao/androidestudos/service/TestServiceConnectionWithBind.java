package com.luizgadao.androidestudos.service;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Toast;

import com.luizgadao.androidestudos.R;
import com.luizgadao.androidestudos.utils.LogUtils;

public class TestServiceConnectionWithBind extends ActionBarActivity implements ServiceConnection{

    private static final String LOG_TAG = TestServiceConnectionWithBind.class.getSimpleName();

    private ServiceConnection connection;
    private MyServiceConnection myServiceConnection;
    private Intent intentService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_with_bind);


        intentService = new Intent("SERVICE_CONNECTION");

        //Context.BIND_AUTO_CREATE;
        //bindService chama apenas o método onCreate do SERVICE.
        connection = this;
        bindService( intentService, connection, Context.BIND_AUTO_CREATE );
    }

    public void startService( View view )
    {
        if ( connection == null )
            connection = TestServiceConnectionWithBind.this;

        bindService( intentService, connection, Context.BIND_AUTO_CREATE );
    }

    public void stopService( View view )
    {
        if ( myServiceConnection.getEnable() )
            unbindService(connection);
    }

    public void getCount( View view )
    {
        Toast.makeText(getBaseContext(), "Count: " + myServiceConnection.getCount(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onServiceConnected(ComponentName componentName, IBinder service) {
        //IBinder service é o retorno no onBind do Service.
        MyServiceConnection.Controller cl  = (MyServiceConnection.Controller) service;
        myServiceConnection = cl.getService();
        //controller.getCount();
    }

    /* não é disparado quando o onDestroy do serviço é chamado.*/
    @Override
    public void onServiceDisconnected(ComponentName componentName) {
        LogUtils.info( LOG_TAG, " - onServiceDisconnected" );
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        // verifica se tem conexão com o serviço, e se tiver verifica se ele tá habilitado.
        // se tiver habilitado desconecta usando o serviço.
        // Caso não faça isso, gera um erro na linha 35.
        if ( myServiceConnection != null & myServiceConnection.getEnable() )
            unbindService( connection );
    }
}
