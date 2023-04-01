package Activity;

import android.annotation.SuppressLint;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.wantedregistery.R;

import JsonParser.JsonWantedDetailParser;
import JsonParser.JsonWantedListParser;
import Model.WantedPerson;
import Model.WantedPersonDetailed;
import Repository.IWantedPersonRepository;
import Repository.WantedPersonRepository;
import Utils.ViewUtils;

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
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

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
    @SuppressLint("ResourceType")
    private void display(WantedPersonDetailed person) {
        LinkedHashMap<String,String> content = person.getAdditionalContent();
        LinkedHashMap<String,String> tableContent = person.getDescriptionContent();
        int index=0;
        ViewUtils.addTitleAndContent((LinearLayout) getResources().getLayout(R.id.linearLayout),"Aliases",content.get("Aliases"), index, 20);
        for (Map.Entry<String, String> entry : tableContent.entrySet()) {
            ViewUtils.addRow(entry.getKey(),entry.getValue(),(TableLayout) getResources().getLayout(R.id.tableLayout),this,5);
        }
        for (Map.Entry<String, String> entry : content.entrySet()) {
            if(!entry.getKey().equals("Aliases")){
                ViewUtils.addTitleAndContent((LinearLayout) getResources().getLayout(R.id.linearLayout),entry.getKey(),entry.getValue(), index, 20);
            }
        }
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
