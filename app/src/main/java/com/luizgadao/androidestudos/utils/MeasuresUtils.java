package com.luizgadao.androidestudos.utils;


import android.util.TypedValue;

import com.luizgadao.androidestudos.app.App;

/**
 * Author luizcarlos
 * Date 26/05/14
 */
public class MeasuresUtils {

    private MeasuresUtils() {
    }

    public static int dipToPixel(float dips) {
        return (int) (dips * App.getApp().getResources().getDisplayMetrics().density + 0.5f);
    }

    public static int pixelToDip(float pixel) {
        return (int) TypedValue.applyDimension( TypedValue.COMPLEX_UNIT_DIP, pixel, App.getApp().getResources().getDisplayMetrics() );
    }
}
