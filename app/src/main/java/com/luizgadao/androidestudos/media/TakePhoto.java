package com.luizgadao.androidestudos.media;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.luizgadao.androidestudos.R;

public class TakePhoto extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_photo);

        Button takePhoto = (Button) findViewById( R.id.take_photo );
        takePhoto.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( "android.media.action.IMAGE_CAPTURE" );
                startActivityForResult( intent, 0 );
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if ( data != null )
        {
            if ( data.getExtras() != null )
            {
                Bitmap bitmap = (Bitmap) data.getExtras().get("data");

                ImageView photo = (ImageView) findViewById(R.id.photo);
                photo.setImageBitmap( bitmap );
            }
        }
    }
}
