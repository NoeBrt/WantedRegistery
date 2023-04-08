package Utils;

import android.graphics.Color;

public class ColorChooser {
    public static int getColorFromText(String text){

        if (text.contains("Missing Person")){
            return Color.argb(255, 255, 204, 255);

        }else if (text.contains("Seeking Information")){
            return Color.argb(255, 0, 102, 204);
        }else{
        return Color.argb(255, 208, 4, 49);}
    }
}
