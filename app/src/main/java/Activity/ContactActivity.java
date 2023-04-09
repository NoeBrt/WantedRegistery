package Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
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

         sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        name = (EditText) findViewById(R.id.nameEditText);
        lastName = (EditText) findViewById(R.id.lastNameEditText);
        email = (EditText) findViewById(R.id.emailEditText);
        phone = (EditText) findViewById(R.id.phoneEditText);

        setupLayout();
    }

    /**
     * Permet d'ajouter les informations de contact dans les EditText à l'ouverture
     */
    public void setupLayout() {
        name.setText(sharedPref.getString("name", ""));
        lastName.setText(sharedPref.getString("lastName", ""));
        email.setText(sharedPref.getString("email", ""));
        phone.setText(sharedPref.getString("phone", ""));
    }

    /**
     * Permet de sauvegarder les informations de contact lors du clic sur le bouton
     * @param v Vue inhérente au onClick
     */
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
    /**
     * Méthode qui s'effectue avant la rotation de l'écran
     */
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    /**
     * Méthode qui s'effectue après rotation (re-setup le layout)
     */
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        setupLayout();
    }
}