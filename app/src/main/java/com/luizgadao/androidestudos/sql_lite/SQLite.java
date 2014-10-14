package com.luizgadao.androidestudos.sql_lite;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.luizgadao.androidestudos.R;
import com.luizgadao.androidestudos.utils.LogUtils;

import java.util.ArrayList;
import java.util.List;

public class SQLite extends ActionBarActivity {

    private static final String TAG = SQLite.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite);

        Button cadastrar = ( Button ) findViewById( R.id.bt_cadastrar );
        cadastrar.setVisibility( View.GONE );

        cadastrar.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick( View view ) {
                User user = new User( "Luiz", "teste@gmail.com", "123456" );
                User user1 = new User( "Marília", "teste@gmail.com", "654123" );

                BD bd = new BD( SQLite.this );
                bd.insert( user );
                bd.insert( user1 );

                Toast.makeText( SQLite.this, "usuários cadastrados", Toast.LENGTH_SHORT ).show();
            }
        } );

        Button listar = ( Button ) findViewById( R.id.bt_listar );
        listar.setVisibility( View.GONE );
        listar.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick( View view ) {
                BD bd = new BD( SQLite.this );
                List<User> users = bd.search();

                for ( User user : users ) {
                    LogUtils.info( TAG, "usuário encontrado: " + user.toString() );
                }
            }
        } );

        showUser();
    }

    private void showUser() {

        BD bd = new BD( SQLite.this );
        List<String> users = new ArrayList<String>();

        for ( User user : bd.search() ) {
            LogUtils.info( TAG, "usuário encontrado: " + user.toString() );
            users.add( user.toString() );
        }

        ListView listView = ( ListView ) findViewById( R.id.listViewUser );

        ArrayAdapter<String> adapter = new ArrayAdapter<String>( this, android.R.layout.simple_list_item_1, users );
        listView.setAdapter( adapter );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.sqlite, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
