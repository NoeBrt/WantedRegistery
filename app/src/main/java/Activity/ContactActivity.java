package Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.wantedregistery.R;

public class ContactActivity extends AppCompatActivity {

    private SharedPreferences sharedPref;
    private EditText name;
    private EditText lastName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        name = (EditText) findViewById(R.id.nameEditText);
        lastName = (EditText) findViewById(R.id.lastNameEditText);

        name.setText(sharedPref.getString("name", ""));
        lastName.setText(sharedPref.getString("lastName", ""));
    }

    public void saveData(View v) {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("name", name.getText().toString());
        editor.putString("lastName", lastName.getText().toString());
        editor.apply();

        Intent i = new Intent(this, WantedRecyclerActivity.class);
        startActivity(i);
    }
}