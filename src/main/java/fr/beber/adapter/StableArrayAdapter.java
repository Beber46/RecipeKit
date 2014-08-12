package fr.beber.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import fr.beber.recipekit.R;
import fr.beber.util.SpeedScrollListener;
import fr.beber.util.ViewRowPlus;

import java.util.HashMap;
import java.util.List;

/**
 * Cette classe permet de créer la liste de recette.
 *
 * @author Bertrand
 * @version 1.0
 * @date 09/08/2014
 */
public class StableArrayAdapter extends GPlusListAdapter {

    private List<String> objects;

    public StableArrayAdapter(final Context context, final SpeedScrollListener scrollListener, final List<String> objects) {
        super(context, scrollListener, objects);
        this.objects = objects;
    }

    @Override
    protected View getRowView(int position,View convertView, final ViewGroup parent) {
        ViewRowPlus viewRowPlus;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.gplus_row, parent, false);
            viewRowPlus = new ViewRowPlus((ImageView) convertView.findViewById(R.id.imageViewRow),(TextView) convertView.findViewById(R.id.textViewTitreRecette));
            convertView.setTag(viewRowPlus);
        }
        else
            viewRowPlus = (ViewRowPlus) convertView.getTag();

        viewRowPlus.getTextView().setText(objects.get(position).toString());

        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }


}
