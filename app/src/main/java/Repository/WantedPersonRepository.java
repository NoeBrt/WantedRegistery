package Repository;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import JsonParser.IJsonParserStrategy;
import JsonParser.JsonWantedDetailParser;
import JsonParser.JsonWantedListParser;
import Model.WantedPerson;
import Model.WantedPersonDetailed;

public class WantedPersonRepository implements IWantedPersonRepository{
    @Override
    public ArrayList<WantedPerson> findWanted() {
        JsonWantedListParser parser = new JsonWantedListParser();
        ArrayList<WantedPerson> response = new ArrayList<>();
        try {
            //Recherche du nombre de pages
            String totalLine1 = requestPage(1);
            System.out.println(totalLine1);
            JSONObject toDecode1 = new JSONObject(totalLine1);
            int nbPages = decodeNbPages(toDecode1);

            for (int page = 1; page <= nbPages; page++) {
                //Exécution de la requête HTTP
                String totalLine2 = requestPage(page);
                //Récupération des données
                JSONObject toDecode2 = new JSONObject(totalLine2);
                //Décode l'objet JSON et récupère le ArrayList
                response.addAll(parser.parseJSON(toDecode2));
            }
        } catch (Exception e) {
            //Gestion des erreurs
            e.printStackTrace();
        }

        return response;
    }


    @Override
    public WantedPersonDetailed findWanted(String name) {
        JsonWantedDetailParser parser = new JsonWantedDetailParser();
        return null;
    }


    private String requestPage(int page)  {
     return request("https://api.fbi.gov/wanted/v1?page=" + page);
    }

    private String requestDetail(String title){
        return request("https://api.fbi.gov/wanted/v1?title="+ title);
    }
    private String request(String request) {
        int retryCount = 0;
        int maxRetries = 5;
        long waitTime = 1000; // Initial wait time in milliseconds

        while (retryCount < maxRetries) {
            try {
                HttpURLConnection connection;
                URL url = new URL(request);
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");

                int responseCode = connection.getResponseCode();
                System.out.println("Response Code: " + responseCode);

                if (responseCode == HttpURLConnection.HTTP_OK) {
                    InputStream inputStream = connection.getInputStream();
                    InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                    String response = "";
                    String line;

                    while ((line = bufferedReader.readLine()) != null) {
                        response += line;
                    }

                    return response;
                } else if (responseCode == 429) {
                    return "429";
                } else {
                    throw new IOException("Request failed with HTTP error code: " + responseCode);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return "";
    }




    private int decodeNbPages(JSONObject jso) throws IndexOutOfBoundsException, JSONException {
        int response;
        int items = jso.getInt("total");
        if (items % 20 == 0) response = items / 20;
        else response = (int) Math.ceil(items / 20);
        return response;
    }




}
