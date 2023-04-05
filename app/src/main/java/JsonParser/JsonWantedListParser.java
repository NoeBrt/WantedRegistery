package JsonParser;

import android.graphics.Bitmap;
import android.text.Html;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import BitmapProcess.BitmapProcess;
import Model.WantedPerson;

public class JsonWantedListParser implements IJsonParserStrategy<ArrayList<WantedPerson>> {
    @Override
    public ArrayList<WantedPerson> parseJSON(JSONObject json) throws JSONException {
        ArrayList<WantedPerson> response = new ArrayList<WantedPerson>();
        JSONArray results = json.getJSONArray("items");
        System.out.println(results.length());
        for (int i = 0; i < 1; i++) {
            String name = results.getJSONObject(i).getString("title");
            String photoURL = results.getJSONObject(i).getJSONArray("images").getJSONObject(0).getString("thumb");
            ArrayList<String> imagesURL = parseArray(results.getJSONObject(i).getJSONArray("images"), "original");
            //

            String subject = "Unknown";
            try {
                subject = results.getJSONObject(i).getJSONArray("subjects").getString(0);
            } catch (JSONException e) {

            }

            String uid = results.getJSONObject(i).getString("uid");
            String weightMin = results.getJSONObject(i).getString("weight_min");
            String weightMax = results.getJSONObject(i).getString("weight_max");
            String weight = weightMin + "-" + weightMax;
            if (weightMin.equals(weightMax)) weight = weightMin;
            String dateOfBirthUsed = results.getJSONObject(i).getString("dates_of_birth_used");
            String age = results.getJSONObject(i).getString("age_range");
            String hair = results.getJSONObject(i).getString("hair");
            String eyes = results.getJSONObject(i).getString("eyes");
            String height = results.getJSONObject(i).getString("height_max");
            String sex = results.getJSONObject(i).getString("sex");
            String race = results.getJSONObject(i).getString("race");
            String nationality = results.getJSONObject(i).getString("nationality");
            String scarsAndMarks = results.getJSONObject(i).getString("scars_and_marks");
            String ncic = results.getJSONObject(i).getString("ncic");
            String reward = results.getJSONObject(i).getString("reward_text");
            String aliases = "None";
            try {
                aliases = results.getJSONObject(i).getJSONArray("aliases").getString(0);
            } catch (JSONException e) {

            }

            String remarks = results.getJSONObject(i).getString("remarks");
            String caution = String.valueOf(Html.fromHtml(results.getJSONObject(i).getString("caution")));
            WantedPerson p = new WantedPerson(photoURL, imagesURL, name, subject, uid, weight, dateOfBirthUsed, age, hair, eyes, height, sex, race, nationality, scarsAndMarks, ncic, reward, aliases, remarks, caution);
            response.add(p);
            System.out.println(p.getName());
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
