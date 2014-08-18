package fr.beber.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import fr.beber.bean.Recette;
import fr.beber.recipekit.R;
import fr.beber.util.SpeedScrollListener;
import fr.beber.util.ViewRowPlus;

import java.util.List;

/**
 * Cette classe permet de créer la liste de recette.
 *
 * @author Beber046
 * @version 1.0
 */
public class StableArrayAdapter extends GPlusListAdapter {

    /**
     * Liste de recette à rajouter dans la listView
     */
    private List<Recette> recetteList;

    /**
     * Constructeur
     *
     * @param context Le contexte courant.
     * @param scrollListener Animation pour la listeView.
     * @param recetteList Liste de recette à rajouter.
     */
    public StableArrayAdapter(final Context context, final SpeedScrollListener scrollListener, final List<Recette> recetteList) {
        super(context, scrollListener, recetteList);
        this.recetteList = recetteList;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected View getRowView(int position, View convertView, final ViewGroup parent) {
        ViewRowPlus viewRowPlus;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.gplus_row, parent, false);
            viewRowPlus = new ViewRowPlus((ImageView) convertView.findViewById(R.id.imageViewRow),
                    (TextView) convertView.findViewById(R.id.textViewTitreRecette), (RatingBar) convertView.findViewById(R.id.ratingBarRowNote));
            convertView.setTag(viewRowPlus);
        } else
            viewRowPlus = (ViewRowPlus) convertView.getTag();

        viewRowPlus.getTextView().setText(recetteList.get(position).getName());
        viewRowPlus.getRatingBar().setRating(recetteList.get(position).getNote());

        return convertView;
    }

    //TODO:normalement il ne faut pas surcharger cette méthode
//    @Override
//    public boolean hasStableIds() {
//        return true;
//    }


}
