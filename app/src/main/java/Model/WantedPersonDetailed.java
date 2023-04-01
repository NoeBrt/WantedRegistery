package Model;

import android.graphics.BitmapFactory;
import android.media.Image;

import java.util.ArrayList;

public class WantedPersonDetailed extends WantedPerson {



    public WantedPersonDetailed(String title, String description, String image) {
        super(title, description, image);
        images = new ArrayList<Image>();
    }

    public WantedPersonDetailed(byte[] photo, String name, String subject) {
        super(photo, name, subject);
        images = new ArrayList<Image>();

    }


    ArrayList<Image> images;

    String dateOfBirthUsed;
    String age;
    String hair;
    String eyes;
    String height;
    String sex;
    String race;
    String nationality;
    String scarsAndMarks;
    String ncic;



    String reward;
    String aliases;
    String remarks;
    String caution;












}
