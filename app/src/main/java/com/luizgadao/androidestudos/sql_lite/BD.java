package com.luizgadao.androidestudos.sql_lite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by luizcarlos on 06/10/14.
 */
public class BD
{
    private SQLiteDatabase sqLiteDatabase;

    public BD( Context context) {
        BDCore bdCore = new BDCore( context );
        sqLiteDatabase = bdCore.getWritableDatabase();
    }

    public void insert( User user )
    {
        ContentValues values = new ContentValues();
        values.put( "name", user.getName() );
        values.put( "email", user.getEmail() );
        values.put( "password", user.getPassword() );

        sqLiteDatabase.insert( "user", null, values );
    }

    public void update( User user )
    {
        ContentValues values = new ContentValues();
        values.put( "name", user.getName() );
        values.put( "email", user.getEmail() );
        values.put( "password", user.getPassword() );

        sqLiteDatabase.update( "user", values, "_id = ?", new String[]{ user.getId()+"" } );
    }

    public void delete( User user )
    {
        sqLiteDatabase.delete( "user", "_id = " + user.getId(), null );
    }

    public List<User> search()
    {
        String[] columns = new String[]{ "_id", "name", "email", "password" };
        Cursor cursor = sqLiteDatabase.query( "user", columns, null, null, null, null, "name ASC" );
        List<User> users = new ArrayList<User>();


        if ( cursor.getCount() > 0 )
        {
            cursor.moveToFirst();

            do {
                User user = new User( cursor.getLong( 0 ),
                        cursor.getString( 1 ),
                        cursor.getString( 2 ),
                        cursor.getString( 3 ) );

                users.add( user );

            }while ( cursor.moveToNext() );

        }

        return users;
    }
}
