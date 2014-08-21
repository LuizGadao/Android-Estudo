package com.luizgadao.androidestudos.contentprovider;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.luizgadao.androidestudos.R;
import com.luizgadao.androidestudos.model.Contact;

import java.io.InputStream;
import java.util.ArrayList;

public class ContentProviderGetContacts extends ActionBarActivity {

    private static final String TAG = ContentProviderGetContacts.class.getSimpleName();

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_provider_get_contacts);

        listView = (ListView) findViewById(R.id.list_contacts);

        getContacts();
    }

    private void getContacts()
    {
        Uri contacts = ContactsContract.Contacts.CONTENT_URI;

        //Cursor cursor = managedQuery(contacts, null, null, null, null);
        String[] projection = { ContactsContract.Contacts._ID, ContactsContract.Contacts.DISPLAY_NAME,
                ContactsContract.Contacts.HAS_PHONE_NUMBER };

        Cursor cursor = getBaseContext().getContentResolver().query( contacts, null, null,  null, null );

        ArrayList<String> names = new ArrayList<String>();

        while( cursor.moveToNext() )
        {
            //String contactName = cursor.getString( 0 );  //cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
            //names.add( contactName );
            //Log.i(TAG, "contato: " + contactName);

            Contact contact = readContacts( cursor );
            names.add( contact.getName() );

            Log.i( TAG, contact.toString() );
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>( this, android.R.layout.simple_list_item_1, names );
        listView.setAdapter( adapter );
    }

    private Contact readContacts( Cursor cursor )
    {
        Contact contact = new Contact();
        contact.setId(cursor.getLong(cursor.getColumnIndex(ContactsContract.Contacts._ID)));
        contact.setName( cursor.getString( cursor.getColumnIndex( ContactsContract.Contacts.DISPLAY_NAME ) ) );
        boolean hasPhone = "1".equals( cursor.getString( cursor.getColumnIndexOrThrow( ContactsContract.Contacts.HAS_PHONE_NUMBER ) ) );
        if ( hasPhone )
            contact.setPhones( loadPhones( contact.getId() ) );

        contact.setPhoto( readPhotoContact( contact.getId() ) );

        return contact;
    }

    private ArrayList<String> loadPhones( long id )
    {
        ArrayList<String> phones = new ArrayList<String>();

        Uri phonesURI = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;  //ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        String query = ContactsContract.CommonDataKinds.Phone._ID + "=" + id;

        Cursor cursor = getBaseContext().getContentResolver().query( phonesURI, null, query, null, null );

        try
        {
            while( cursor.moveToNext() )
            {
                String phone = cursor.getString( cursor.getColumnIndex( ContactsContract.CommonDataKinds.Phone.NUMBER ) );

                if (phone != null)
                    phones.add( phone );
            }
        }
        finally {
            cursor.close();
        }

        return phones;
    }

    private Bitmap readPhotoContact( long id )
    {
        Uri uri = ContentUris.withAppendedId( ContactsContract.Contacts.CONTENT_URI, id );
        ContentResolver contentResolver = getBaseContext().getContentResolver();
        InputStream inputStream = ContactsContract.Contacts.openContactPhotoInputStream( contentResolver, uri);

        if ( inputStream != null )
            return BitmapFactory.decodeStream( inputStream );

        return  null;
    }

}
