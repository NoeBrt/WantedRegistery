package Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import DAO.DBHandler;
import com.example.wantedregistery.R;
import Adapter.WantedAdapter;
import JsonParser.JsonWantedListParser;
import Model.WantedPerson;
import Repository.IWantedPersonRepository;
import Repository.WantedPersonRepository;

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

public class WantedRecyclerActivity extends AppCompatActivity {

    DBHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wanted_recycler);

        db = new DBHandler(this);

        if (internetConnectionTest()) {
            db.deleteForm("wanted");

            IWantedPersonRepository wantedPersonRepository = new WantedPersonRepository();
            RequestTask requestTask = new RequestTask(wantedPersonRepository);
            requestTask.execute();

        } else display(25);
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

    public void display(int limit) {
        ArrayList<WantedPerson> P = db.select(limit);

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
        i.putExtra("Title", ((TextView) view.findViewById(R.id.wantedName)).getText().toString() );
        startActivity(i);
    }

    public void editContact(View view) {
        Intent i = new Intent(this, ContactActivity.class);
        startActivity(i);
    }

    private class RequestTask extends AsyncTask<Void, Void, ArrayList<WantedPerson>> {

        private final IWantedPersonRepository wantedPersonRepository;

        private RequestTask(IWantedPersonRepository wantedPersonRepository) {
            this.wantedPersonRepository = wantedPersonRepository;
        }

        @Override
        protected ArrayList<WantedPerson> doInBackground(Void... voids) {
            return wantedPersonRepository.findWanted();

        }



        protected void onPostExecute(@NonNull ArrayList<WantedPerson> result) {
            if (result.size() > 1) {
                for (WantedPerson p : result) {
                    db.insertWanted(p.getPhotoByte(), p.getName(), p.getSubject());
                }
            }
            System.out.println("result: " + result.size());
            display(25);
        }
    }
}