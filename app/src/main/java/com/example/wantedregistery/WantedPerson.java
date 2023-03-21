package com.example.wantedregistery;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;

import java.io.InputStream;

public class WantedPerson {

    private Bitmap photo;
    private String name;
    private String subject;

    public WantedPerson(String photoURL, String name, String subject) {
        this.name = name;
        this.subject = subject;

        try {
            InputStream in = new java.net.URL(photoURL).openStream();
            photo = BitmapFactory.decodeStream(in);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Bitmap getPhoto() {
        return photo;
    }

    public void setPhoto(String photoURL) {
        try {
            InputStream in = new java.net.URL(photoURL).openStream();
            photo = BitmapFactory.decodeStream(in);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
