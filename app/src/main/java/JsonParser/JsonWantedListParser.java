package JsonParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import Model.WantedPerson;

public class JsonWantedListParser implements IJsonParserStrategy<ArrayList<WantedPerson>> {
    @Override
    public ArrayList<WantedPerson> parseJSON(JSONObject json) throws JSONException {
        ArrayList<WantedPerson> response = new ArrayList<WantedPerson>();
        JSONArray results = json.getJSONArray("items");
        for (int i = 0; i < 20; i++) {
            String name = results.getJSONObject(i).getString("title");
            String photoURL = results.getJSONObject(i).getJSONArray("images").getJSONObject(0).getString("thumb");
            String subject = "Unknown";
            subject = results.getJSONObject(i).getJSONArray("subjects").getString(0);
            String uid = results.getJSONObject(i).getString("uid");

            WantedPerson p = new WantedPerson(photoURL, name, subject,uid);
            response.add(p);
            System.out.println(p.getName());
        }
        return response;

    }

}
