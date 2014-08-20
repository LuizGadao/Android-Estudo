package com.luizgadao.androidestudos.model;

import android.content.ContentUris;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.ContactsContract;

import java.util.ArrayList;

/**
 * Created by luizcarlos on 20/08/14.
 */
public class Contact
{

    private String name;
    private long id;
    private Bitmap photo;
    private ArrayList<String> phones;

    public Contact() {
    }

    public Contact(String name, long id, Bitmap photo, ArrayList<String> phones) {
        this.name = name;
        this.id = id;
        this.photo = photo;
        this.phones = phones;
    }

    public Uri getUri()
    {
        return ContentUris.withAppendedId( ContactsContract.Contacts.CONTENT_URI, id );
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Bitmap getPhoto() {
        return photo;
    }

    public void setPhoto(Bitmap photo) {
        this.photo = photo;
    }

    public ArrayList<String> getPhones() {
        return phones;
    }

    public void setPhones(ArrayList<String> phones) {
        this.phones = phones;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", photo=" + photo +
                ", phones=" + phones +
                '}';
    }
}
