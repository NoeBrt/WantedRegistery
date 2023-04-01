package JsonParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import Model.WantedPerson;

public class JsonWantedDetailParser implements IJsonParserStrategy<ArrayList<WantedPerson>> {

    @Override
    public ArrayList<WantedPerson> parseJSON(JSONObject jso) throws JSONException {
        ArrayList<WantedPerson> response = new ArrayList<WantedPerson>();
        JSONObject results = (JSONObject) jso.getJSONArray("items").getJSONObject(0);
        System.out.println(results.toString());


        return response;
    }

}
