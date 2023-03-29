package com.example.wantedregistery;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import org.json.JSONArray;
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
import java.util.List;

public class WantedRecyclerActivity extends AppCompatActivity {

    DBHandler db;
    int nbPages = (int) Double.POSITIVE_INFINITY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wanted_recycler);

        db = new DBHandler(this);

        if (internetConnectionTest()) {
            db.deleteForm("wanted");
            int page = 1;
            while (page <= 1) {
                WantedRecyclerActivity.RequestTask requestTask = new WantedRecyclerActivity.RequestTask();
                requestTask.execute(page);
                page++;
            }
        } else displayAll();
    }

    private boolean internetConnectionTest() {
        try {
            ConnectivityManager cm = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo nInfo = cm.getActiveNetworkInfo();
            boolean connected = (nInfo != null) && nInfo.isAvailable() && nInfo.isConnected();

            return connected;
        } catch (Exception e) {
            return false;
        }
    }

    public void displayAll() {
        ArrayList<WantedPerson> P = db.selectAll();

        if (P.size() > 0) {
            WantedAdapter myAdapter;
            RecyclerView recycler = (RecyclerView)
                    findViewById(R.id.recyclerWanted);
            recycler.setLayoutManager(new
                    LinearLayoutManager(WantedRecyclerActivity.this));
            myAdapter = new WantedAdapter(P);
            recycler.setAdapter(myAdapter);
        }
    }

    public void displayDetails(View view) {
        Intent i = new Intent(this, WantedDetailsActivity.class);
        startActivity(i);
    }

    @Deprecated
    private class RequestTask extends AsyncTask<Integer, Void, ArrayList<WantedPerson>> {

        int page;

        @Override
        protected ArrayList<WantedPerson> doInBackground(Integer... page) {
            this.page = page[0];
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
                URL url = new URL("https://api.fbi.gov/wanted/v1?page=" + page);
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

        private ArrayList<WantedPerson> decodeJSON(JSONObject jso) throws IndexOutOfBoundsException, JSONException {
            ArrayList<WantedPerson> response = new ArrayList<WantedPerson>();
            if (true) {
                int items = jso.getInt("total");
                if (items % 20 == 0) nbPages = items / 20;
                else nbPages = (int) Math.ceil(items / 20);

                JSONArray results = jso.getJSONArray("items");
                for (int i = 0; i < 20; i++) {
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
                for (WantedPerson p : result) {
                    db.insertWanted(p.getPhotoByte(), p.getName(), p.getSubject());
                }
            }

            displayAll();
        }
    }
}