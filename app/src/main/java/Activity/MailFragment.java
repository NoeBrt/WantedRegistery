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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_text, container, false);
    }
    public void onViewCreated(View view, Bundle savedInstanceState) {
        view.findViewById(R.id.sendButton).setOnClickListener((View.OnClickListener) this);
        view.findViewById(R.id.cancelButton).setOnClickListener((View.OnClickListener) this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.sendButton) {
            sendEmail();
        } else if (v.getId() == R.id.cancelButton) {
            System.out.println("cancel");
            getActivity().getSupportFragmentManager().popBackStack();
        }

    }
private void sendEmail(){
    Intent email = new Intent(Intent.ACTION_SENDTO);
    email.setData(Uri.parse("mailto:" + getString(R.string.email)));
    email.putExtra(Intent.EXTRA_SUBJECT, "information");
    email.putExtra(Intent.EXTRA_TEXT, "test");
    if (email.resolveActivity(getActivity().getPackageManager()) != null) {
        startActivity(Intent.createChooser(email, "Choose an Email client :"));
    } else {
        Toast.makeText(getActivity(), "No email app found", Toast.LENGTH_SHORT).show();
    }
}


}