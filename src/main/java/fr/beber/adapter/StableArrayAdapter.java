package fr.beber.adapter;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.HashMap;
import java.util.List;

/**
 * Cette classe permet de créer la liste de recette.
 *
 * @author Bertrand
 * @version 1.0
 * @date 09/08/2014
 */
public class StableArrayAdapter extends ArrayAdapter<String> {

    private HashMap<String, Integer> mIdMap = new HashMap<String, Integer>();

    public StableArrayAdapter(final Context context, int textViewResourceId, final List<String> objects) {
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
