package Model;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class WantedPersonDetailed extends WantedPerson {

    public WantedPersonDetailed() {
        super();
        images = new ArrayList<Bitmap>();
        aliases = new ArrayList<String>();

    }

    public WantedPersonDetailed(String uid,String weight,String photoURL, String name, String subject, ArrayList<Bitmap> images, String dateOfBirthUsed, String age, String hair, String eyes, String height, String sex, String race, String nationality, String scarsAndMarks, String ncic, String reward, ArrayList<String> aliases, String remarks, String caution) {
        super(photoURL, name, subject,uid);
        this.images = images;
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

    public WantedPersonDetailed(String uid,String weight,byte[] photo, String name, String subject, ArrayList<Bitmap> images, String dateOfBirthUsed, String age, String hair, String eyes, String height, String sex, String race, String nationality, String scarsAndMarks, String ncic, String reward, ArrayList<String> aliases, String remarks, String caution) {
        super(photo, name, subject,uid);
        this.images = images;
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

    public WantedPersonDetailed(ArrayList<Bitmap> images, String dateOfBirthUsed, String age, String hair, String eyes, String height, String sex, String race, String nationality, String scarsAndMarks, String ncic, String reward, ArrayList<String> aliases, String remarks, String caution) {
        this.images = images;
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
    }

    ArrayList<Bitmap> images;

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

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    String weight;


    String reward;
    ArrayList<String> aliases;
    String remarks;
    String caution;



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


    public ArrayList<String> getAliases() {
        return aliases;
    }
    public void setAliases(ArrayList<String> aliases) {
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

    public ArrayList<Bitmap> getImages() {
        return images;
    }

    public void setImages(ArrayList<Bitmap> images) {
        this.images = images;
    }
    public LinkedHashMap<String,String> getDescriptionContent(){
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

    public LinkedHashMap<String,String> getAdditionalContent(){
        LinkedHashMap<String,String> content = new LinkedHashMap<String,String>();
        String aliasesText= String.join(",", aliases);
        content.put("Aliases", aliasesText);
        content.put("Reward", reward);
        content.put("Remarks", remarks);
        content.put("Caution", caution);
        return content;
    }

}
