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
    private EditText email;
    private EditText phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        name = (EditText) findViewById(R.id.nameEditText);
        lastName = (EditText) findViewById(R.id.lastNameEditText);
        email = (EditText) findViewById(R.id.emailEditText);
        phone = (EditText) findViewById(R.id.phoneEditText);

        setupLayout();
    }

    public void setupLayout() {
        name.setText(sharedPref.getString("name", ""));
        lastName.setText(sharedPref.getString("lastName", ""));
        email.setText(sharedPref.getString("email", ""));
        phone.setText(sharedPref.getString("phone", ""));
    }

    public void saveData(View v) {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("name", name.getText().toString());
        editor.putString("lastName", lastName.getText().toString());
        editor.putString("email", email.getText().toString());
        editor.putString("phone", phone.getText().toString());
        editor.apply();

        Intent i = new Intent(this, WantedRecyclerActivity.class);
        startActivity(i);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        setupLayout();
    }
}