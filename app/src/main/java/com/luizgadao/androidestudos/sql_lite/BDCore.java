package com.luizgadao.androidestudos.sql_lite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by luizcarlos on 06/10/14.
 */
public class BDCore extends SQLiteOpenHelper {

    private static final String NAME_BD = "TEST";
    private static final int VERSION_BD = 1;

    /*
    private static final String NAME_TABLE = "user";
    private static final String LABEL_ID = "_id";
    private static final String LABEL_NAME = "name";
    private static final String LABEL_EMAIL = "email";
    private static final String LABEL_PASSWORD = "password";
    private static final String NOTNULL = "not null";
    private static final String AUTO_INCREMENT = "autoincrement";
    */


    public BDCore( Context context ) {
        super( context, NAME_BD, null, VERSION_BD );
    }

    @Override
    public void onCreate( SQLiteDatabase sqLiteDatabase ) {
        sqLiteDatabase.execSQL( "create table user( _id integer primary key autoincrement, name text not null, email text not null, password text not null );" );
    }

    @Override
    public void onUpgrade( SQLiteDatabase sqLiteDatabase, int i, int i2 ) {
        sqLiteDatabase.execSQL( "drop table user;" );
        onCreate( sqLiteDatabase );
    }
}
