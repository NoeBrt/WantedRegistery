package JsonParser;

import android.graphics.Bitmap;
import android.media.Image;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import BitmapProcess.BitmapProcess;
import Model.WantedPerson;
import Model.WantedPersonDetailed;

public class JsonWantedDetailParser implements IJsonParserStrategy<WantedPerson> {

    @Override
    public WantedPerson parseJSON(JSONObject jso) throws JSONException {
        System.out.println("JSON "+jso);
        ArrayList<WantedPerson> response = new ArrayList<WantedPerson>();
        WantedPersonDetailed p = new WantedPersonDetailed();
        ArrayList<String> aliases=parseArray(jso.getJSONArray("aliases"));
        ArrayList<String> images=parseArray(jso.getJSONArray("images"),"original");
        ArrayList<Bitmap> imagesBitmap=new ArrayList<Bitmap>();
        images.forEach((image)->{
            imagesBitmap.add( (new BitmapProcess()).fromURLtoBitmap(image));
        });
        p.setImages(imagesBitmap);
        p.setUid(jso.getString("uid"));
        p.setAliases(aliases);
        p.setPhoto((new BitmapProcess()).fromURLtoBitmap(jso.getJSONArray("images").getJSONObject(0).getString("original")));
        p.setCaution(jso.getString("caution"));
        p.setRemarks(jso.getString("remarks"));
        p.setReward(jso.getString("reward"));
        p.setNcic(jso.getString("ncic"));
        p.setScarsAndMarks(jso.getString("scars_and_marks"));
        p.setRace(jso.getString("race_raw"));
        p.setDateOfBirthUsed(jso.getString("date_of_birth"));
        p.setAge(jso.getString("age_range"));
        p.setHair(jso.getString("hair"));
        p.setEyes(jso.getString("eyes"));
        p.setHeight(jso.getString("height_max"));
        p.setWeight(jso.getString("weight"));
        p.setSex(jso.getString("sex"));
        p.setNationality(jso.getString("nationality"));
        p.setName(jso.getString("title"));
        p.setSubject(jso.getString("subject"));
        return p;
    }

    private ArrayList<String> parseArray(JSONArray arrayJson) throws JSONException {
        ArrayList<String> response = new ArrayList<String>();
        for (int i = 0; i < arrayJson.length(); i++) {
            response.add(arrayJson.getString(i));
        }
        return response;
    }

    private ArrayList<String> parseArray(JSONArray arrayJson,String key) throws JSONException {
        ArrayList<String> response = new ArrayList<String>();
        for (int i = 0; i < arrayJson.length(); i++) {
            response.add(arrayJson.getJSONObject(i).getString(key));
        }
        return response;
    }

}
