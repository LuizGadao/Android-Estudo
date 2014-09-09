package com.luizgadao.androidestudos;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.luizgadao.androidestudos.contentprovider.ContentProviderGetContacts;
import com.luizgadao.androidestudos.media.TakePhoto;
import com.luizgadao.androidestudos.ui.MyGallery;
import com.luizgadao.androidestudos.ui.MyGalleryWithImageSwitcher;
import com.luizgadao.androidestudos.ui.MySpinner;
import com.luizgadao.androidestudos.ui.MyViewPager;
import com.luizgadao.androidestudos.ui.view.MyCustomView;
import com.luizgadao.androidestudos.ui.view.MyCustomView2;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final ArrayList<Class> classes = new ArrayList<Class>();
        classes.add( MyGallery.class );
        classes.add( MyGalleryWithImageSwitcher.class );
        classes.add( MyViewPager.class );
        classes.add( ContentProviderGetContacts.class );
        classes.add( TakePhoto.class );
        classes.add( MySpinner.class );
        classes.add( TestOnSaveInstanceState.class );
        classes.add( MyCustomView.class );
        classes.add( MyCustomView2.class );

        ArrayList<String> activitiesName = new ArrayList<String>(); // = {"Simple Gallery", "Gallery with ImageSwithcer", "View Pager", "Get Contacts", "Take Photo", "Spinner", "Teste with OnSaveInstaceState"};

        for( Class myClass : classes )
        {
            activitiesName.add( myClass.getSimpleName() );
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>( getBaseContext(), android.R.layout.simple_list_item_1, activitiesName );

        ListView listView = (ListView) findViewById( R.id.listview );
        listView.setAdapter(adapter);

        listView.setOnItemClickListener( new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                //Intent intent = new Intent( getBaseContext(), classes.get(position) );
                //startActivity( intent );
                initActivity( classes.get(position) );
            }
        });

        //automate start my activity for test
        initActivity( classes.get( classes.size()-1 ) );
    }

    private void initActivity( Class myClass )
    {
        Intent intent = new Intent( getBaseContext(), myClass );
        startActivity( intent );
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
