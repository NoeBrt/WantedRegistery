package com.example.wantedregistery;

import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class WantedAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<WantedPerson> P;

    public WantedAdapter(ArrayList<WantedPerson> P) {
        this.P = P;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int
            viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.wanted_layout, parent, false);
        return new ViewHolderWanted(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int
            position) {
        ((ViewHolderWanted)
                holder).getImageViewPhoto().setImageURI(P.get(position).getPhoto());
        ((ViewHolderWanted)
                holder).getTextViewName().setText(P.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return P.size();
    }

    public static class ViewHolderWanted extends RecyclerView.ViewHolder {

        private final ImageView photo;
        private final TextView name;
        public ViewHolderWanted(View view) {
            super(view);
            photo = view.findViewById(R.id.wantedImage);
            name = view.findViewById(R.id.wantedName);
        }

        public ImageView getImageViewPhoto() { return photo; }

        public TextView getTextViewName() {
            return name;
        }
    }
}
