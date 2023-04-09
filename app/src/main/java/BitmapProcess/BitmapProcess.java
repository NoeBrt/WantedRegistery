package BitmapProcess;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.InputStream;

public class BitmapProcess {

    /**
     * Permet la conversion d'un URL d'une image vers un objet Bitmap
     * @param photoURL URL de l'image à convertir
     * @return Bitmap de l'image convertie
     */
    public Bitmap fromURLtoBitmap(String photoURL) {
        try {
            InputStream in = new java.net.URL(photoURL).openStream();
            System.out.println("photoURL: " + in);
            return BitmapFactory.decodeStream(in);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
