package com.example.wantedregistery;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONArray;
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

public class WantedRecyclerActivity extends AppCompatActivity {

    ImageView photoView;
    TextView nameView;
    DBHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wanted_recycler);

        db = new DBHandler(this);

        WantedRecyclerActivity.RequestTask requestTask = new WantedRecyclerActivity.RequestTask();
        requestTask.execute();
    }

    @Deprecated
    private class RequestTask extends AsyncTask<Void, Void, ArrayList<WantedPerson>> {

        @Override
        protected ArrayList<WantedPerson> doInBackground(Void... voids) {
            ArrayList<WantedPerson> response = new ArrayList<>();
            try {
                //Exécution de la requête HTTP
                String totalLine = requete();
                //Récupération des données
                JSONObject toDecode = new JSONObject(totalLine);
                //Décode l'objet JSON et récupère le ArrayList
                response = decodeJSON(toDecode);
            } catch (Exception e) {
                //Gestion des erreurs
                e.printStackTrace();
            }

            return response;
        }

        private String requete() {
            String response = "";
            System.out.println("Testing request");
            try {
                HttpURLConnection connection = null;
                URL url = new URL("https://api.fbi.gov/wanted/v1");
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                InputStream inputStream = connection.getInputStream();
                InputStreamReader inputStreamReader = new
                        InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String ligne = bufferedReader.readLine() ;
                while (ligne!= null){
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

        private ArrayList<WantedPerson> decodeJSON(JSONObject jso) throws Exception {
            ArrayList<WantedPerson> response = new ArrayList<WantedPerson>();
            if (true) {
                JSONArray results = jso.getJSONArray("items");
                for (int i = 0; i < 10; i++) {
                    String name = results.getJSONObject(i).getString("title");
                    String photoURL = results.getJSONObject(i).getJSONArray("images").getJSONObject(0).getString("thumb");
                    String subject = results.getJSONObject(i).getJSONArray("subjects").getString(0);
                    WantedPerson p = new WantedPerson(photoURL, name, subject);
                    response.add(p);
                }
            }

            return response;
        }

        protected void onPostExecute(@NonNull ArrayList<WantedPerson> result) {
            if (result.size() > 1) {
                WantedAdapter myAdapter;
                RecyclerView recycler = (RecyclerView)
                        findViewById(R.id.recyclerWanted);
                recycler.setLayoutManager(new
                        LinearLayoutManager(WantedRecyclerActivity.this));
                myAdapter = new WantedAdapter(result);
                recycler.setAdapter(myAdapter);
            }
        }
    }
}