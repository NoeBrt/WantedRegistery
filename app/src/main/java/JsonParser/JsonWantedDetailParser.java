package JsonParser;

import android.graphics.Bitmap;
import android.text.Html;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import BitmapProcess.BitmapProcess;
import Model.WantedPerson;

/**
 * CLASSE INUTILISE DESORMAIS
 */
public class JsonWantedDetailParser implements IJsonParserStrategy<WantedPerson> {

    @Override
    public WantedPerson parseJSON(JSONObject jso) throws JSONException {
        System.out.println("JSON " + jso);
        ArrayList<WantedPerson> response = new ArrayList<WantedPerson>();
        WantedPerson p = new WantedPerson();
        ArrayList<String> aliases = new ArrayList<>();
        if (jso.getString("aliases") != "null") {
            aliases = parseArray(jso.getJSONArray("aliases"));
        } else {
            aliases.add("null");
        }

        ArrayList<String> images = parseArray(jso.getJSONArray("images"),"original");
        ArrayList<Bitmap> imagesBitmap = new ArrayList<Bitmap>();
        ArrayList<String> subjects = parseArray(jso.getJSONArray("subjects"));
        images.forEach((image) -> {
            imagesBitmap.add( (new BitmapProcess()).fromURLtoBitmap(image));
        });

        p.setSubject(String.join(",",subjects));
        //p.setImages(imagesBitmap);
        p.setUid(jso.getString("uid"));
        p.setAliases(aliases.get(0));
        p.setPhoto((new BitmapProcess()).fromURLtoBitmap(jso.getJSONArray("images").getJSONObject(0).getString("original")));
        p.setCaution(String.valueOf(Html.fromHtml(jso.getString("caution"))));
        p.setRemarks(jso.getString("remarks"));
        p.setReward(jso.getString("reward_text"));
        p.setNcic(jso.getString("ncic"));
        p.setScarsAndMarks(jso.getString("scars_and_marks"));
        p.setRace(jso.getString("race_raw"));
        p.setDateOfBirthUsed(jso.getString("dates_of_birth_used"));
        p.setAge(jso.getString("age_range"));
        p.setHair(jso.getString("hair"));
        p.setEyes(jso.getString("eyes"));
        p.setHeight(jso.getString("height_max"));
        p.setWeight(jso.getString("weight"));
        p.setSex(jso.getString("sex"));
        p.setNationality(jso.getString("nationality"));
        p.setName(jso.getString("title"));

        return p;
    }

    private ArrayList<String> parseArray(JSONArray arrayJson) throws JSONException {
        ArrayList<String> response = new ArrayList<String>();
        for (int i = 0; i < arrayJson.length(); i++) {
            response.add(arrayJson.getString(i));
        }
        return response;
    }

    private ArrayList<String> parseArray(JSONArray arrayJson, String key) throws JSONException {
        ArrayList<String> response = new ArrayList<String>();
        for (int i = 0; i < arrayJson.length(); i++) {
            response.add(arrayJson.getJSONObject(i).getString(key));
        }

        return response;
    }
}
