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

import JsonParser.JsonWantedDetailParser;
import JsonParser.JsonWantedListParser;
import Model.WantedPerson;

public class WantedPersonRepository implements IWantedPersonRepository {
    @Override
    public ArrayList<WantedPerson> findWanted() {
        JsonWantedListParser parser = new JsonWantedListParser();
        ArrayList<WantedPerson> response = new ArrayList<>();
        try {
            //Recherche du nombre de pages
            String totalLine1 = requestPage(1);
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
    public WantedPerson findWanted(String uid) {
        JsonWantedDetailParser parser = new JsonWantedDetailParser();
        try {
            JSONObject toDecode = new JSONObject(requestDetail(uid));
            return parser.parseJSON(toDecode);
        } catch (Exception e) {
            //Gestion des erreurs
            e.printStackTrace();
            return null;
        }
    }

    private String requestPage(int page) {
        return request("https://api.fbi.gov/@wanted?pageSize=50&page=" + page);
    }

    private String requestDetail(String uid) {
        return request("https://api.fbi.gov/@wanted-person/" + uid);
    }

    private String request(String request) {
        System.out.println(request);
        String response = "";
        System.out.println("Testing request");
        try {
            HttpURLConnection connection = null;
            URL url = new URL(request);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            InputStream inputStream = connection.getInputStream();
            InputStreamReader inputStreamReader = new
                    InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String ligne = bufferedReader.readLine();
            while (ligne != null) {
                response += ligne;
                ligne = bufferedReader.readLine();
            }
        } catch (UnsupportedEncodingException e) {
            response = "Problème d'encodage !";
        } catch (MalformedURLException e) {
            response = "Problème d'URL !";
        } catch (IOException e) {
            response = "Problème de connexion !";
        } catch (Exception e) {
            e.printStackTrace();
        }

        return response;
    }

    private int decodeNbPages(JSONObject jso) throws IndexOutOfBoundsException, JSONException {
        int response;
        int items = jso.getInt("total");
        if (items % 50 == 0) response = items / 50;
        else response = (int) Math.ceil(items / 50) + 1;
        return response;
    }
}
