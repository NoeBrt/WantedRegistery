package Activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.wantedregistery.R;

import JsonParser.JsonWantedDetailParser;
import JsonParser.JsonWantedListParser;
import Model.WantedPerson;
import Model.WantedPersonDetailed;
import Repository.IWantedPersonRepository;
import Repository.WantedPersonRepository;

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

String uid;
ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wanted_details);
        uid=getIntent().getStringExtra("uid");
        imageView=findViewById(R.id.imageView2);
        IWantedPersonRepository wantedPersonRepository = new WantedPersonRepository();
        RequestTask requestTask = new RequestTask(wantedPersonRepository);
        requestTask.execute();
    }
    private void display(WantedPersonDetailed result) {
        System.out.println(result);
    }

    public void switchImage(){


    }



    private class RequestTask extends AsyncTask<Void, Void, WantedPersonDetailed> {

        private final IWantedPersonRepository wantedPersonRepository;

        private RequestTask(IWantedPersonRepository wantedPersonRepository) {
            this.wantedPersonRepository = wantedPersonRepository;
        }

        @Override
        protected WantedPersonDetailed doInBackground(Void... voids) {
            return wantedPersonRepository.findWanted(uid);

        }



        protected void onPostExecute(@NonNull WantedPersonDetailed result) {


            display(result);
        }
    }




}
