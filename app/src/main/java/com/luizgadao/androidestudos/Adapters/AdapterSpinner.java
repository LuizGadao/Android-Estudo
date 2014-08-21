package com.luizgadao.androidestudos.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by luizcarlos on 21/08/14.
 */
public class AdapterSpinner extends BaseAdapter
{
    private Context context;
    private String[] list;

    public AdapterSpinner(Context context, String[] list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.length;
    }

    @Override
    public Object getItem(int position  ) {
        return list[ position ];
    }

    @Override
    public long getItemId(int id) {
        return id;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        TextView textView = new TextView( context );
        textView.setText( list[ position ] );
        textView.setTextColor( Color.RED );

        return textView;
    }
}
