package fr.beber.adapter;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.HashMap;
import java.util.List;

/**
 * Cette classe permet de créer une liste généric. Cette liste se compose de String pour assigner un nom à chaque
 * liste.
 *
 * @author Beber046
 * @version 1.0
 */
public class GenericBaseAdapter extends ArrayAdapter<String> {

    private HashMap<String, Integer> mIdMap = new HashMap<String, Integer>();

    /**
     * Contructeur, instancie la liste de string
     *
     * @param context            Le contexte courant.
     * @param textViewResourceId
     * @param objects
     */
    public GenericBaseAdapter(final Context context, int textViewResourceId, final List<String> objects) {
        super(context, textViewResourceId, objects);
        for (int i = 0; i < objects.size(); ++i) {
            mIdMap.put(objects.get(i), i);
        }
    }

    @Override
    public long getItemId(int position) {
        String item = getItem(position);
        return mIdMap.get(item);
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }
}
