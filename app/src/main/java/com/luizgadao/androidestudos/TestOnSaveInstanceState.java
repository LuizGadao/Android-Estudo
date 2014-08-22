package com.luizgadao.androidestudos;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.luizgadao.androidestudos.utils.BitmapSerializable;
import com.luizgadao.androidestudos.utils.ListImgs;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class TestOnSaveInstanceState extends ActionBarActivity {

    private static final String TAG = TestOnSaveInstanceState.class.getSimpleName();

    private ArrayList<BitmapSerializable> imgs = new ArrayList<BitmapSerializable>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_on_save_instance_state);

        if ( savedInstanceState != null )
        {
            Log.i(TAG, "on-create with bundle");

            ListImgs listImgs = (ListImgs) savedInstanceState.getSerializable( ListImgs.Key );
            imgs = listImgs.getImgs();

            addImgs();
        }
        else {
            Log.i(TAG, "on-create without bundle");
            loadImgs();
        }
    }

    private void loadImgs() {
        new Thread(){

            @Override
            public void run() {

                try
                {
                    for( int i = 0; i < 30; i++ )
                    {
                        URL url = new URL( "http://s.glbimg.com/es/sde/f/equipes/2013/12/16/flamengo_45x45.png" );
                        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                        InputStream inputStream = urlConnection.getInputStream();
                        imgs.add(
                                new BitmapSerializable( BitmapFactory.decodeStream( inputStream ) )
                        );
                    }
                }
                catch ( IOException e)
                {
                    e.printStackTrace();
                }

                runOnUiThread( new Runnable() {
                    @Override
                    public void run() {
                        addImgs();
                    }
                } );

            }
        }.start();
    }

    private void addImgs() {

        LinearLayout linearLayout = (LinearLayout) findViewById( R.id.linearlayout_save );

        for ( int i = 0; i < imgs.size(); i++ )
        {
            ImageView imageView = new ImageView(this);
            imageView.setImageBitmap( imgs.get( i ).getBitmap() );

            linearLayout.addView( imageView );
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putSerializable( ListImgs.Key, new ListImgs( imgs ) );
    }
}
