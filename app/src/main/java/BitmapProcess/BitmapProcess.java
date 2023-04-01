package BitmapProcess;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.InputStream;

public class BitmapProcess {


    public Bitmap fromURLtoBitmap(String photoURL){
     try {
        InputStream in = new java.net.URL(photoURL).openStream();
        System.out.println("photoURL: "+in);
       return BitmapFactory.decodeStream(in);
    } catch (Exception e) {
        e.printStackTrace();
        return null;
    }
    }

}
