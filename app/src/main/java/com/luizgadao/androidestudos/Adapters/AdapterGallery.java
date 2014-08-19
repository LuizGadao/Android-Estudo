package com.luizgadao.androidestudos.Adapters;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by luizcarlos on 19/08/14.
 */
public class AdapterGallery extends BaseAdapter
{

    private Context context;
    private int[] imgs;

    public AdapterGallery(Context context, int[] imgs) {
        this.context = context;
        this.imgs = imgs;
    }

    @Override
    public int getCount() {
        return imgs.length;
    }

    @Override
    public Object getItem(int position) {
        return imgs[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        LinearLayout linearLayout = new LinearLayout(context);
        Gallery.LayoutParams layoutParams = new Gallery.LayoutParams( Gallery.LayoutParams.WRAP_CONTENT,  Gallery.LayoutParams.WRAP_CONTENT );
        linearLayout.setOrientation( LinearLayout.VERTICAL );
        linearLayout.setLayoutParams( layoutParams );

        TextView textView = new TextView( context );
        textView.setLayoutParams( layoutParams );
        textView.setText( "Carro: " + position );
        textView.setGravity(Gravity.CENTER);

        linearLayout.addView(textView);

        ImageView imageView = new ImageView( context );
        imageView.setLayoutParams( layoutParams );
        imageView.setImageResource( imgs[ position ] );

        linearLayout.addView( imageView );

        return linearLayout;

        /*
        ImageView imageView = new ImageView( context );

        Gallery.LayoutParams layoutParams = new Gallery.LayoutParams( Gallery.LayoutParams.WRAP_CONTENT,  Gallery.LayoutParams.WRAP_CONTENT );
        imageView.setLayoutParams( layoutParams );

        imageView.setImageResource( imgs[ position ] );

        return imageView;
        */
    }
}
