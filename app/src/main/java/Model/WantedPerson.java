package Model;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
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

    public WantedPerson(byte[] photo, String name, String subject) {
        this.photo = BitmapFactory.decodeByteArray(photo, 0, photo.length);
        this.name = name;
        this.subject = subject;
    }

    public Bitmap getPhoto() {
        return photo;
    }

    public byte[] getPhotoByte() {
        try {
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            photo.compress(Bitmap.CompressFormat.PNG, 100, stream);
            byte[] b = stream.toByteArray();

            stream.close();
            return b;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
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
