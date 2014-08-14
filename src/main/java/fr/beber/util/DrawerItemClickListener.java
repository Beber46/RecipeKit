package fr.beber.util;

import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

/**
 * Cette classe permet de créer l'évènement lorsque l'on clique sur l'un des champs du menu.
 *
 * @author Beber046
 * @version 1.0
 */
public class DrawerItemClickListener implements ListView.OnItemClickListener {


    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;

    public DrawerItemClickListener(final DrawerLayout drawerLayout, final ListView listView) {
        mDrawerLayout = drawerLayout;
        mDrawerList = listView;
    }

    @Override
    public void onItemClick(final AdapterView parent,final View view, int position, long id) {
        mDrawerLayout.closeDrawer(mDrawerList);
    }

}
