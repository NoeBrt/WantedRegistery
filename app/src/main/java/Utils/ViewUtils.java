package Utils;

import android.content.Context;
import android.graphics.Typeface;
import android.text.Layout;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.Objects;

public class ViewUtils {

    /**
     * Ajoute un titre et un contenu textuel à notre layout
     * @param layout Layout en question
     * @param title Texte du titre
     * @param content Contenu textuel
     * @param id ID du layout
     * @param TitleSize Taille du titre
     */
    public static void addTitleAndContent(LinearLayout layout, String title, String content, int id, float TitleSize){
        if (content.equals("null"))
            return;
        layout.addView(createTextTitle(layout.getContext(), title, id, TitleSize));
        layout.addView(createTextContent(layout.getContext(), content,id + 1));
    }

    /**
     * Créer un vue titre à partir d'un texte
     * @param context Contexte de l'activite
     * @param title Texte du titre
     * @param id ID de la vue
     * @param size Taille du titre
     * @return Vue du titre sous forme de TextView
     */
    public static TextView createTextTitle(Context context, String title, int id, float size) {
        TextView titleView = new TextView(context);
        titleView.setText(title);
        titleView.setTextSize(size);
        titleView.setTypeface(Typeface.DEFAULT_BOLD);
        titleView.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        titleView.setId(id);
        return titleView;
    }

    /**
     * Créer une nouvelle vue pour chaque élément textuel de l'enquête
     * @param context Context de l'activité
     * @param content Contenu textuel
     * @param id ID de la vue
     * @return Nouveau TextView à ajouter au layout
     */
    public static TextView createTextContent(Context context, String content, int id) {
        TextView contentView = new TextView(context);
        contentView.setText(content);
        contentView.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        contentView.setId(id);
        return contentView;
    }

    /**
     * Ajoute une nouvelle ligne au tableau de données qui décrivent une personne dans sa page
     * @param title
     * @param content
     * @param tableLayout
     * @param context
     */
    public static void addRow(String title, String content, TableLayout tableLayout, Context context) {
        if (!content.equals("null")) {
            TableRow row = new TableRow(context);
            row.setLayoutParams(new TableLayout.LayoutParams(
                    TableLayout.LayoutParams.MATCH_PARENT,
                    TableLayout.LayoutParams.WRAP_CONTENT));
            TextView titleView = createTextContent(context, title, 50);
            titleView.setLayoutParams(new TableRow.LayoutParams(
                    TableRow.LayoutParams.WRAP_CONTENT,
                    TableRow.LayoutParams.WRAP_CONTENT));
            titleView.setGravity(Gravity.CENTER_VERTICAL);
            titleView.setTypeface(Typeface.DEFAULT_BOLD);
            TextView contentView = createTextContent(context, content, 51);
            contentView.setLayoutParams(new TableRow.LayoutParams(
                    TableRow.LayoutParams.WRAP_CONTENT,
                    TableRow.LayoutParams.WRAP_CONTENT));
            contentView.setGravity(Gravity.CENTER_VERTICAL);

            row.addView(titleView);
            row.addView(contentView);
            tableLayout.addView(row);
        }
    }
}