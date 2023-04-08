package Model;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import BitmapProcess.BitmapProcess;

public class WantedPerson {

    private Bitmap photo;
    private ArrayList<String> images;
    private String name;
    private String subject;
    private String uid;
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
    String weight;
    String reward;
    String aliases;
    String remarks;
    String caution;

    public WantedPerson(String photoURL, ArrayList<String> imagesURL, String name, String subject, String uid, String weight, String dateOfBirthUsed, String age, String hair, String eyes, String height, String sex, String race, String nationality, String scarsAndMarks, String ncic, String reward, String aliases, String remarks, String caution) {
        photo = (new BitmapProcess()).fromURLtoBitmap(photoURL);
        images = imagesURL;
        this.name = name;
        this.subject = subject;
        this.uid = uid;
        this.dateOfBirthUsed = dateOfBirthUsed;
        this.age = age;
        this.hair = hair;
        this.eyes = eyes;
        this.height = height;
        this.sex = sex;
        this.race = race;
        this.nationality = nationality;
        this.scarsAndMarks = scarsAndMarks;
        this.ncic = ncic;
        this.reward = reward;
        this.aliases = aliases;
        this.remarks = remarks;
        this.caution = caution;
        this.weight = weight;

        System.out.println("photoURL : " + photoURL);
    }

    public WantedPerson(byte[] photo, ArrayList<String> images, String name, String subject, String uid, String weight, String dateOfBirthUsed, String age, String hair, String eyes, String height, String sex, String race, String nationality, String scarsAndMarks, String ncic, String reward, String aliases, String remarks, String caution) {
        this.photo = BitmapFactory.decodeByteArray(photo, 0, photo.length);
        this.images = images;
        this.name = name;
        this.subject = subject;
        this.uid = uid;
        this.dateOfBirthUsed = dateOfBirthUsed;
        this.age = age;
        this.hair = hair;
        this.eyes = eyes;
        this.height = height;
        this.sex = sex;
        this.race = race;
        this.nationality = nationality;
        this.scarsAndMarks = scarsAndMarks;
        this.ncic = ncic;
        this.reward = reward;
        this.aliases = aliases;
        this.remarks = remarks;
        this.caution = caution;
        this.weight = weight;
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
       photo = (new BitmapProcess()).fromURLtoBitmap(photoURL);
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

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getDateOfBirthUsed() {
        return dateOfBirthUsed;
    }

    public void setDateOfBirthUsed(String dateOfBirthUsed) {
        this.dateOfBirthUsed = dateOfBirthUsed;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getHair() {
        return hair;
    }

    public void setHair(String hair) {
        this.hair = hair;
    }

    public String getEyes() {
        return eyes;
    }

    public void setEyes(String eyes) {
        this.eyes = eyes;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getScarsAndMarks() {
        return scarsAndMarks;
    }

    public void setScarsAndMarks(String scarsAndMarks) {
        this.scarsAndMarks = scarsAndMarks;
    }

    public String getNcic() {
        return ncic;
    }

    public void setNcic(String ncic) {
        this.ncic = ncic;
    }

    public String getReward() {
        return reward;
    }

    public void setReward(String reward) {
        this.reward = reward;
    }

    public String getAliases() {
        return aliases;
    }

    public void setAliases(String aliases) {
        this.aliases = aliases;
    }
    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getCaution() {
        return caution;
    }

    public void setCaution(String caution) {
        this.caution = caution;
    }


    /*
    public ArrayList<byte[]> getImagesBytes() {
        ArrayList<byte[]> imagesByte = new ArrayList<>();
        for (Bitmap image : images) {
            try {
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                image.compress(Bitmap.CompressFormat.PNG, 100, stream);
                byte[] b = stream.toByteArray();

                stream.close();
                imagesByte.add(b);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return imagesByte;
    }
*/
    public LinkedHashMap<String,String> getDescriptionContent() {
        LinkedHashMap<String,String> content = new LinkedHashMap<String,String>();
        content.put("Date of Birth",dateOfBirthUsed);
        content.put("Age",age);
        content.put("Hair",hair);
        content.put("Eyes",eyes);
        content.put("Height",height);
        content.put("Sex", sex);
        content.put("Race", race);
        content.put("Nationality", nationality);
        content.put("Scars and Marks", scarsAndMarks);
        content.put("NCIC", ncic);
        return content;
    }

    public LinkedHashMap<String,String> getAdditionalContent() {
        LinkedHashMap<String,String> content = new LinkedHashMap<String,String>();
        String aliasesText= String.join(",", aliases);
        content.put("Aliases", aliasesText);
        content.put("Reward", reward);
        content.put("Remarks", remarks);
        content.put("Caution", caution);
        return content;
    }

    public ArrayList<String> getImages() {
        return images;
    }

    public void setImages(ArrayList<String> images) {
        this.images = images;
    }
}
