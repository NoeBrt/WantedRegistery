package Activity;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.wantedregistery.R;

import DAO.DBHandler;
import Model.WantedPerson;
import Repository.IWantedPersonRepository;
import Repository.WantedPersonRepository;
import Utils.ViewUtils;

import java.util.LinkedHashMap;
import java.util.Map;

public class WantedDetailsActivity extends AppCompatActivity {

    DBHandler db;
    String uid;
    LinearLayout imageLayout;
    HorizontalScrollView imageScrollView;
    TextView title;
    WantedPerson person;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wanted_details);

        db = new DBHandler(this);

        uid = getIntent().getStringExtra("uid");
        imageLayout = findViewById(R.id.ImageLayout);
        imageScrollView = findViewById(R.id.ImageScrollView);
        title = findViewById(R.id.indiv_title);
        /* IWantedPersonRepository wantedPersonRepository = new WantedPersonRepository();
        RequestTask requestTask = new RequestTask(wantedPersonRepository);
        requestTask.execute(); */

        WantedPerson wantedPerson = db.select(uid);
        display(wantedPerson);
        addAllImages(wantedPerson);
    }


    private void display(WantedPerson person) {
        this.person = person;
        title.setText(person.getName());

        addAllImages(person);
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
            if (!entry.getKey().equals("Aliases")){
                ViewUtils.addTitleAndContent((LinearLayout) findViewById(R.id.linearLayoutIndividual),
                        entry.getKey(),entry.getValue(), index, 20);
                index++;
            }
        }
    }

    private void addAllImages(WantedPerson person) {
        for (Bitmap image : person.getImagesBitmap()){
            addImage(image);
        }
    }

    private void addImage(Bitmap photo) {
        ImageView img = new ImageView(this);
        img.setImageBitmap(photo);
        /*  int marginInPixels = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_PT, 4, getResources().getDisplayMetrics());
        ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) img.getLayoutParams();
        layoutParams.setMargins(marginInPixels, marginInPixels, marginInPixels, marginInPixels);*/
        //img.setLayoutParams(layoutParams);

        imageLayout.addView(img);
    }

    private class RequestTask extends AsyncTask<Void, Void, WantedPerson> {

        private final IWantedPersonRepository wantedPersonRepository;

        private RequestTask(IWantedPersonRepository wantedPersonRepository) {
            this.wantedPersonRepository = wantedPersonRepository;
        }

        @Override
        protected WantedPerson doInBackground(Void... voids) {
            return wantedPersonRepository.findWanted(uid);
        }

        protected void onPostExecute(@NonNull WantedPerson result) {
            display(result);
        }
    }
}
