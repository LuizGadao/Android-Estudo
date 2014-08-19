package com.luizgadao.androidestudos.ui;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import com.luizgadao.androidestudos.Adapters.AdapterGallery;
import com.luizgadao.androidestudos.R;

public class MyGallery extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        int imgs[] = { R.drawable.ferrari_laferrari, R.drawable.bugatti_veyron, R.drawable.lamborghini_veneno,
                        R.drawable.maserati_gran_turismo, R.drawable.mclaren, R.drawable.pagani_zonda,
                        R.drawable.porsche_911 };

        AdapterGallery adapterGallery = new AdapterGallery( getBaseContext(), imgs );

        android.widget.Gallery gallery = (android.widget.Gallery) findViewById(R.id.gallery);
        gallery.setAdapter( adapterGallery );
    }

}
