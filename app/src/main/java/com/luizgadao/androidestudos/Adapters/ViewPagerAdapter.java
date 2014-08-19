package com.luizgadao.androidestudos.Adapters;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by luizcarlos on 19/08/14.
 */
public class ViewPagerAdapter extends PagerAdapter {

    private Context context;
    private int[] imgs;

    public ViewPagerAdapter(Context baseContext, int[] imgs)
    {
        this.context = baseContext;
        this.imgs = imgs;
    }

    @Override
    public int getCount() {
        return imgs.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        Log.i("ViewPagerAdapter", "isViewFromObject view == object " + (view == object) );
        Log.i("ViewPagerAdapter", "isViewFromObject view == object.parent " + (view == ((TextView)object).getParent()) );
        return view == ((TextView)object).getParent();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        linearLayout.setLayoutParams( layoutParams );

        container.addView( linearLayout );

        ImageView imageView = new ImageView(context);
        imageView.setImageResource( imgs[ position ] );

        linearLayout.addView( imageView );

        TextView textView = new TextView(context);
        textView.setText( "carro: " + position );

        linearLayout.addView( textView );

        Log.i( "ViewPageAdapter", "build carro: " + position );

        //return linearLayout;

        return textView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object view) {
        //super.destroyItem(container, position, object);
        Log.i( "ViewPageAdapter", "destroy carro: " + position );
        container.removeView((View) ((TextView)view).getParent() );

    }
}
