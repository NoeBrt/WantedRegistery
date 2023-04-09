package Activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.wantedregistery.R;

public class MailFragment extends Fragment implements View.OnClickListener {

    String uid;
    String name;

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
        String body = "I have more informations about the  following case : '" + name + "'. Please, contact me for more.";
        String emailString = mailTo + "?subject=" + Uri.encode(subject) + "&body=" + Uri.encode(body);
        Uri emailUri = Uri.parse(emailString);
        email.setData(emailUri);

        if (email.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivity(Intent.createChooser(email, "Choose an Email client :"));
        } else {
            Toast.makeText(getActivity(), "No email app found", Toast.LENGTH_SHORT).show();
        }
    }
}