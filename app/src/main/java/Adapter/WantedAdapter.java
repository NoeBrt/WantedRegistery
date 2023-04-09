package Adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wantedregistery.R;
import Model.WantedPerson;
import Utils.ColorChooser;

import java.util.ArrayList;

public class WantedAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<WantedPerson> P;

    public WantedAdapter(ArrayList<WantedPerson> P) {
        this.P = P;
    }

    /**
     * Gère la création d'une nouvelle instance de wanted_layout.xml
     * @param parent The ViewGroup into which the new View will be added after it is bound to
     *               an adapter position.
     * @param viewType The view type of the new View.
     *
     * @return La nouvelle instance de wanted_layout.xml
     */
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int
            viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.wanted_layout, parent, false);
        return new ViewHolderWanted(view);
    }

    /**
     * Permet l'affichage des données à l'intérieur de notre nouveau wanted_layout.xml d'une personne recherchée
     * @param holder The ViewHolder which should be updated to represent the contents of the
     *        item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ImageView image = ((ViewHolderWanted) holder).getImageViewPhoto();
        image.setImageBitmap(P.get(position).getPhoto());
        image.setBackgroundColor(ColorChooser.getColorFromText(P.get(position).getSubject()));
        ((ViewHolderWanted) holder).getTextViewName().setText(P.get(position).getName());
        ((ViewHolderWanted) holder).getTextViewSubject().setText(P.get(position).getSubject());
        ((ViewHolderWanted) holder).getUid().setText(P.get(position).getUid());
    }

    /**
     * Permet de retourner le nombre de persones présentes dans la liste P
     * @return Le nombre de personnes présentes dans la liste P
     */
    @Override
    public int getItemCount() {
        return P.size();
    }

    /**
     * Classe qui gère la création de notre nouveau layout
     */
    public static class ViewHolderWanted extends RecyclerView.ViewHolder {

        private final ImageView photo;
        private final TextView name;
        private final TextView subject;
        private final TextView uid;

        /**
         * Constructeur qui associe les attributs de la classe aux vues du layout
         * @param view
         */
        public ViewHolderWanted(View view) {
            super(view);
            photo = view.findViewById(R.id.wantedImage);
            name = view.findViewById(R.id.wantedName);
            subject = view.findViewById(R.id.wantedSubject);
            uid = view.findViewById(R.id.uid);
        }

        public ImageView getImageViewPhoto() { return photo; }

        public TextView getTextViewName() { return name; }

        public TextView getTextViewSubject() { return subject; }

        public TextView getUid() { return uid; }
    }
}
