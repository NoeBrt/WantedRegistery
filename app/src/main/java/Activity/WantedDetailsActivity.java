package Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.example.wantedregistery.R;

import DAO.DBHandler;
import Model.WantedPerson;
import Utils.ColorChooser;
import Utils.ViewUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class WantedDetailsActivity extends AppCompatActivity {

    DBHandler db;
    String uid;
    LinearLayout imageLayout;
    HorizontalScrollView imageScrollView;
    TextView title;
    WantedPerson person;
    TextView subject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wanted_details);

        db = new DBHandler(this);

        uid = getIntent().getStringExtra("uid");

        imageLayout = findViewById(R.id.ImageLayout);
        imageScrollView = findViewById(R.id.ImageScrollView);
        title = findViewById(R.id.indiv_title);
        subject = findViewById(R.id.indiv_subject);

        WantedPerson wantedPerson = db.select(uid);
        try {
            display(wantedPerson);
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    private void display(WantedPerson person) throws IOException, URISyntaxException {
        this.person = person;
        title.setText(person.getName());
        subject.setText(person.getSubject());
        subject.setTextColor(ColorChooser.getColorFromText(person.getSubject()));

        loadImagesFromUrl(person.getImages().toArray(new String[0]));
        LinkedHashMap<String,String> content = person.getAdditionalContent();
        LinkedHashMap<String,String> tableContent = person.getDescriptionContent();
        System.out.println("Table content : " + tableContent);

        int index=0;
        ViewUtils.addTitleAndContent((LinearLayout) findViewById(R.id.linearLayoutIndividual),
                "Aliases",content.get("Aliases"), index, 20);
        index++;
        for (Map.Entry<String, String> entry : tableContent.entrySet()) {
            ViewUtils.addRow(entry.getKey(),entry.getValue(),
                    (TableLayout) findViewById(R.id.tableLayout),this);
            index++;
        }

        for (Map.Entry<String, String> entry : content.entrySet()) {
            if (!entry.getKey().equals("Aliases")){
                ViewUtils.addTitleAndContent((LinearLayout) findViewById(R.id.linearLayoutIndividual),
                        entry.getKey(),entry.getValue(), index, 20);
                index++;
            }
        }
    }
    private static final int REQUEST_CALL_PHONE_PERMISSION = 1;

    public void callButtonClicked(View view) {
        String phoneNumber = "911"; // phone number to call

        Intent callIntent = new Intent(Intent.ACTION_DIAL);
        callIntent.setData(Uri.parse("tel:" + phoneNumber));
        startActivity(callIntent);
    }

    public void mailButtonClicked(View view) {
        System.out.println("mail button clicked");
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.email_fragment, new MailFragment());
        ft.addToBackStack(null);
        ft.commit();
    }

    @SuppressLint("StaticFieldLeak")
    private void loadImagesFromUrl(String ...imageUrl) {
        new AsyncTask<String, Void, ArrayList<Bitmap>>() {
            @Override
            protected ArrayList<Bitmap> doInBackground(String... urls) {
                ArrayList<Bitmap> bitmaps = new ArrayList<>();
                try {
                    for (String url : urls){
                        InputStream inputStream = new URL(url).openStream();
                        bitmaps.add(BitmapFactory.decodeStream(inputStream));
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
                return bitmaps;
            }

            @Override
            protected void onPostExecute(ArrayList<Bitmap> bitmap) {
                for (Bitmap b : bitmap){
                    addImage(b);
                }
            }
        }.execute(imageUrl);
    }

    private void addImage(Bitmap bitmap){
        ImageView imageView = new ImageView(this);
        imageView.setImageBitmap(bitmap);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, // width
                LinearLayout.LayoutParams.WRAP_CONTENT // height
        );

        int marginInPx = 16; //Convert from dp to pixels if needed
        layoutParams.setMargins(marginInPx, marginInPx, marginInPx, marginInPx);
        imageView.setLayoutParams(layoutParams);
        //imageView.setScrollContainer(true);
        imageView.setCropToPadding(false);
        imageLayout.addView(imageView);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        WantedPerson wantedPerson = db.select(uid);
        try {
            display(wantedPerson);
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}
