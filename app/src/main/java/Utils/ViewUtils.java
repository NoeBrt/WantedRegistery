package Utils;

import android.content.Context;
import android.graphics.Typeface;
import android.text.Layout;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.Objects;

public class ViewUtils {

    public static void addTitleAndContent(LinearLayout layout, String title,String content, int id, float TitleSize){
        if(content.equals("null"))
            return;
        layout.addView(createTextTitle(layout.getContext(),title,id,TitleSize));
        layout.addView(createTextContent(layout.getContext(),content,id+1));
    }

    public static TextView createTextTitle(Context context, String title, int id,float size) {
        TextView titleView = new TextView(context);
        titleView.setText(title);
        titleView.setTextSize(size);
        titleView.setTypeface(Typeface.DEFAULT_BOLD);
        titleView.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        titleView.setPadding(5, 5, 2, 0);
        titleView.setId(id);
        return titleView;

    }

    public static TextView createTextContent(Context context, String content, int id) {
        TextView contentView = new TextView(context);
        contentView.setText(content);
        contentView.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        contentView.setId(id);
        return contentView;
    }
    public static void addRow(String title, String content, TableLayout tableLayout, Context context, float keySize){
        if (content.equals("null")){
        TableRow row = new TableRow(context);
        row.setLayoutParams(new TableLayout.LayoutParams(
                TableLayout.LayoutParams.MATCH_PARENT,
                TableLayout.LayoutParams.WRAP_CONTENT));
        row.addView(createTextTitle(context,title, View.generateViewId(),keySize));
        row.addView(createTextContent(context,content, View.generateViewId()));
        tableLayout.addView(row);
        }
    }

}