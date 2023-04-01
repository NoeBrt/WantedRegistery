package Model;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import BitmapProcess.BitmapProcess;

public class WantedPerson {

    private Bitmap photo;
    private String name;
    private String subject;
    private String uid;




    public WantedPerson(String photoURL, String name, String subject, String uid) {
        this.name = name;
        this.subject = subject;
        this.uid = uid;
        photo= (new BitmapProcess()).fromURLtoBitmap(photoURL);
        System.out.println("photoURL: "+photoURL);

    }

    public WantedPerson(byte[] photo, String name, String subject, String uid) {
        this.photo = BitmapFactory.decodeByteArray(photo, 0, photo.length);
        this.name = name;
        this.subject = subject;
        this.uid = uid;
    }
    public WantedPerson() {
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
       photo= (new BitmapProcess()).fromURLtoBitmap(photoURL);
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

    public void setPhoto(Bitmap photo) {
        this.photo = photo;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
