package com.luizgadao.androidestudos.ui;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ViewSwitcher;

import com.luizgadao.androidestudos.Adapters.AdapterGallery;
import com.luizgadao.androidestudos.R;

public class MyGalleryWithImageSwitcher extends ActionBarActivity implements ViewSwitcher.ViewFactory {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_gallery_with_image_switcher);

        final int imgs[] = { R.drawable.ferrari_laferrari, R.drawable.bugatti_veyron, R.drawable.lamborghini_veneno,
                R.drawable.maserati_gran_turismo, R.drawable.mclaren, R.drawable.pagani_zonda,
                R.drawable.porsche_911};

        //ImageSwitcher needs implementation of view factory
        final ImageSwitcher imageSwitcher = (ImageSwitcher) findViewById(R.id.image_switcher);
        imageSwitcher.setFactory( this );
        imageSwitcher.setInAnimation(AnimationUtils.loadAnimation(this, android.R.anim.fade_out));
        imageSwitcher.setOutAnimation(AnimationUtils.loadAnimation(this, android.R.anim.fade_in));

        AdapterGallery adapterGallery = new AdapterGallery( getBaseContext(), imgs );

        android.widget.Gallery gallery = (android.widget.Gallery) findViewById(R.id.gallery_switcher);
        gallery.setAdapter( adapterGallery );
        gallery.setOnItemSelectedListener( new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                imageSwitcher.setImageResource( imgs[ position ] );
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    @Override
    public View makeView() {

        ImageView imageView = new ImageView(this);
        imageView.setLayoutParams( new ImageSwitcher.LayoutParams(ImageSwitcher.LayoutParams.MATCH_PARENT, ImageSwitcher.LayoutParams.MATCH_PARENT ) );

        return imageView;
    }
}
