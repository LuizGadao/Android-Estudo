package com.luizgadao.androidestudos.ui;

import android.app.SearchManager;
import android.content.Context;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.SearchView;
import android.view.MenuItem;
import android.view.View;

import com.luizgadao.androidestudos.app.App;
import com.luizgadao.androidestudos.utils.LogUtils;

/**
 * Created by luizcarlos on 26/09/14.
 */
public class MySearchView
{

    private static final String LOG = MySearchView.class.getSimpleName();
    private SearchView searchView;

    public MySearchView( MenuItem menuItem ) {

        SearchManager searchManager = (SearchManager) App.getApp().getSystemService( Context.SEARCH_SERVICE );
        searchView = (SearchView) MenuItemCompat.getActionView(menuItem);

        //searchView.setSearchableInfo(searchManager.getSearchableInfo( activity.getComponentName() ));

        searchView.setOnQueryTextListener( new OnQueryTextListener() );
        searchView.setOnFocusChangeListener( new OnFocusChangeListener() );
        searchView.setOnCloseListener( new OnCloseListener() );
    }

    public class OnQueryTextListener implements SearchView.OnQueryTextListener
    {
        @Override
        public boolean onQueryTextSubmit(String s) {
            LogUtils.info( LOG, "onQueryTextSubmit: " + s );
            // true quando consumir o evento.
            return true;
        }

        @Override
        public boolean onQueryTextChange(String s) {
            LogUtils.info( LOG, "onQueryTextChange: " + s );
            //true quando consumir o evento.
            return true;
        }
    }

    public class OnFocusChangeListener implements View.OnFocusChangeListener
    {
        @Override
        public void onFocusChange(View view, boolean b) {
            LogUtils.info( LOG, "focus: " + b );
        }
    }

    public class OnCloseListener implements SearchView.OnCloseListener
    {

        @Override
        public boolean onClose() {
            LogUtils.info( LOG, "onClose SearchView");
            return false;
        }
    }
}
