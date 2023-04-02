package Activity;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
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
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class WantedDetailsActivity extends AppCompatActivity {

String uid;
LinearLayout imageLayout;
HorizontalScrollView imageScrollView;

TextView title;

WantedPersonDetailed person;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wanted_details);
        uid=getIntent().getStringExtra("uid");
        imageLayout=findViewById(R.id.ImageLayout);
        imageScrollView=findViewById(R.id.ImageScrollView);
        title=findViewById(R.id.indiv_title);
        IWantedPersonRepository wantedPersonRepository = new WantedPersonRepository();
        RequestTask requestTask = new RequestTask(wantedPersonRepository);
        requestTask.execute();
    }


    private void display(WantedPersonDetailed person) {
        this.person=person;
        title.setText(person.getName());

        addAllImage(person);
        LinkedHashMap<String,String> content = person.getAdditionalContent();
        LinkedHashMap<String,String> tableContent = person.getDescriptionContent();

        int index=0;
        ViewUtils.addTitleAndContent((LinearLayout) findViewById(R.id.linearLayoutIndividual),
                "Aliases",content.get("Aliases"), index, 20);
        index++;
        for (Map.Entry<String, String> entry : tableContent.entrySet()) {
            ViewUtils.addRow(entry.getKey(),entry.getValue(),
                    (TableLayout) findViewById(R.id.tableLayout),this,5);
            index++;
        }

        for (Map.Entry<String, String> entry : content.entrySet()) {
            if(!entry.getKey().equals("Aliases")){
                ViewUtils.addTitleAndContent((LinearLayout) findViewById(R.id.linearLayoutIndividual),
                        entry.getKey(),entry.getValue(), index, 20);
                index++;
            }
        }
    }

    private void addAllImage(WantedPersonDetailed person) {
        for (Bitmap image : person.getImages()){
            addImage(image);

        }
    }

    private void addImage(Bitmap photo){
        ImageView img = new ImageView(this);
        img.setImageBitmap(photo);
        int marginInPixels = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_PT, 4, getResources().getDisplayMetrics());
        ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) img.getLayoutParams();
        layoutParams.setMargins(marginInPixels, marginInPixels, marginInPixels, marginInPixels);
        img.setLayoutParams(layoutParams);

        imageLayout.addView(img);
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
