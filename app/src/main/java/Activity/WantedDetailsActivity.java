package Activity;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.wantedregistery.R;

import JsonParser.JsonWantedDetailParser;
import JsonParser.JsonWantedListParser;
import Model.WantedPerson;
import Repository.IWantedPersonRepository;

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

public class WantedDetailsActivity extends AppCompatActivity {

String Title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wanted_details);
        Title=getIntent().getStringExtra("Title");
    }

    private class RequestTask extends AsyncTask<Void, Void, WantedPerson> {

        private final IWantedPersonRepository wantedPersonRepository;

        private RequestTask(IWantedPersonRepository wantedPersonRepository) {
            this.wantedPersonRepository = wantedPersonRepository;
        }

        @Override
        protected WantedPerson doInBackground(Void... voids) {
            return wantedPersonRepository.findWanted(Title);

        }



        protected void onPostExecute(@NonNull ArrayList<WantedPerson> result) {

            //display(result);
        }
    }


}
