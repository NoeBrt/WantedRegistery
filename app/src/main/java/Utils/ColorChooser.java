package Utils;

import android.graphics.Color;

public class ColorChooser {

    /**
     * Permet de choisir le couleur à associer à une personne recherchée
     * @param text Type d'enquête menée
     * @return Nombre entier de la couleur
     */
    public static int getColorFromText(String text){
        if (text.contains("Missing Person")){
            return Color.argb(255, 255, 204, 255);

        }else if (text.contains("Seeking Information")){
            return Color.argb(255, 0, 102, 204);
        }else{
        return Color.argb(255, 208, 4, 49);}
    }
}
