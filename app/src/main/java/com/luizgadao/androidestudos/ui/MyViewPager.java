package com.luizgadao.androidestudos.ui;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.widget.LinearLayout;

import com.luizgadao.androidestudos.Adapters.ViewPagerAdapter;
import com.luizgadao.androidestudos.R;

public class MyViewPager extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);

        int imgs[] = { R.drawable.ferrari_laferrari, R.drawable.bugatti_veyron, R.drawable.lamborghini_veneno,
                R.drawable.maserati_gran_turismo, R.drawable.mclaren, R.drawable.pagani_zonda,
                R.drawable.porsche_911 };

        //ViewPager viewPager = (ViewPager) findViewById( R.id.viewpager );
        ViewPager viewPager = new ViewPager(this);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams( LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT );
        viewPager.setLayoutParams(layoutParams);

        viewPager.setAdapter( new ViewPagerAdapter( getBaseContext(), imgs ) );

        viewPager.setOnPageChangeListener( new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                Log.i("MyViewPager", "onPageScrolled-position: " + position);
            }

            @Override
            public void onPageSelected(int position) {
                Log.i("MyViewPager", "onPageSelected-position: " + position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                Log.i("MyViewPager", "onPageScrollStateChanged-position: " + state);
            }
        });

        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.linearlayout);
        linearLayout.addView( viewPager );
    }

}
