package Activity;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.wantedregistery.R;

public class MailFragment extends Fragment implements View.OnClickListener {

    String uid;
    String name;
    EditText body;
    SharedPreferences sharedPref;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //Inflate the layout for this fragment
        uid = getArguments().getString("uid");
        name = getArguments().getString("name");

        return inflater.inflate(R.layout.fragment_text, container, false);
    }

    /**
     * Méthode qui s'effectue automatiquement lorsque la vue est créée
     * (ici, ajoute le onClick sur les boutons)
     * @param view The View returned by {@link #onCreateView(LayoutInflater, ViewGroup, Bundle)}.
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     * from a previous saved state as given here.
     */
    public void onViewCreated(View view, Bundle savedInstanceState) {
        body=view.findViewById(R.id.mailText);
        sharedPref = PreferenceManager.getDefaultSharedPreferences(getActivity());

        String namePref=  sharedPref.getString("name", "");
        String lastnamePref= sharedPref.getString("lastName", "");
        String emailPref= sharedPref.getString("email", "");
        String phonePref= sharedPref.getString("phone", "");
        String bodyText="";
       bodyText+="My name is "+namePref+" "+lastnamePref+" and my email is "+emailPref+" and my phone number is "+phonePref+"";
       bodyText+="\n\"I have more informations about the  following case : "+ name + "'. Please, contact me for more.";
        body.setText(bodyText);


        view.findViewById(R.id.sendButton).setOnClickListener((View.OnClickListener) this);
        view.findViewById(R.id.cancelButton).setOnClickListener((View.OnClickListener) this);
    }

    @Override
    /**
     * Gestion du clic sur le bouton de validation ou d'annulation
     */
    public void onClick(View v) {
        if (v.getId() == R.id.sendButton) {
            sendEmail();
        } else if (v.getId() == R.id.cancelButton) {
            System.out.println("Cancelling");
            getActivity().getSupportFragmentManager().popBackStack();
        }
    }

    /**
     * Permet d'envoyer un mail lorsque le bouton flottant est pressé
     */
    private void sendEmail() {
        Intent email = new Intent(Intent.ACTION_SENDTO);

        String mailTo = "mailto:" + getString(R.string.email);
        String subject = "[AUTOMATIC MESSAGE] Informations about case n°" + uid;
        String emailString = mailTo + "?subject=" + Uri.encode(subject) + "&body=" + Uri.encode(body.getText().toString());
        Uri emailUri = Uri.parse(emailString);
        email.setData(emailUri);

        if (email.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivity(Intent.createChooser(email, "Choose an Email client :"));
            getActivity().getSupportFragmentManager().popBackStack();
        } else {
            Toast.makeText(getActivity(), "No email app found, please install one", Toast.LENGTH_SHORT).show();
        }
    }
}