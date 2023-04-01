package JsonParser;

import org.json.JSONException;
import org.json.JSONObject;

public interface IJsonParserStrategy<N> {
  N parseJSON(JSONObject jso) throws JSONException;
}
