package fr.beber.recipekit;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import fr.beber.adapter.StableArrayAdapter;
import fr.beber.bdd.InitBD;
import fr.beber.bdd.dao.RecetteDAO;
import fr.beber.bean.Recette;
import fr.beber.util.DrawerItemClickListener;
import fr.beber.util.SpeedScrollListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Cette classe permet de Créer la première page de la pre-alpha
 *
 * @author Beber46
 * @version 1.0
 */
public class AccueilActivity extends Activity {

    private SpeedScrollListener listener;
    private ActionBarDrawerToggle mDrawerToggle;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);

        this.initMDrawer();

        final InitBD initBD = new InitBD();

        final RecetteDAO recetteDAO = new RecetteDAO(this);
        recetteDAO.openOnlyRead();
        if (recetteDAO.getAll().size() < 1) {
            recetteDAO.close();
            initBD.createRecetteMike(this);
            recetteDAO.openOnlyRead();
        }

        listener = new SpeedScrollListener();
        final ListView listView = (ListView) findViewById(R.id.listeViewRecette);
        listView.setOnScrollListener(listener);

        final StableArrayAdapter stableArrayAdapter = new StableArrayAdapter(this,
                listener, recetteDAO.getAll());


        recetteDAO.close();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                final Intent intent = new Intent(getApplicationContext(), RecettePreviewActivity.class);
                startActivity(intent);
            }
        });

        listView.setAdapter(stableArrayAdapter);
    }

    /**
     * Permet d'initialiser l'ActionBarDrawerToggle
     */
    private void initMDrawer() {
        final String[] mMenuStrings = getResources().getStringArray(R.array.drawer_menu);
        final DrawerLayout mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        final ListView mDrawerList = (ListView) findViewById(R.id.left_drawer);

        mDrawerList.setAdapter(new ArrayAdapter<String>(this, R.layout.drawer_list, mMenuStrings));
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener(mDrawerLayout, mDrawerList));
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.drawable.ic_drawer, R.string.drawer_open, R.string.drawer_close) {

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                //getActionBar().setTitle(mTitle);
            }

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                //getActionBar().setTitle(mDrawerTitle);
            }
        };

        mDrawerLayout.setDrawerListener(mDrawerToggle);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {

        final MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_activity, menu);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            final ActionBar actionBar = getActionBar();
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                if (mDrawerToggle.onOptionsItemSelected(item)) {
                    return true;
                }
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * Permet de construire une liste de string de nom de recette.
     *
     * @param recetteList Liste de {@link fr.beber.bean.Recette}
     * @return Liste de nom de recette.
     */
    private List<String> constructionListeRecette(final List<Recette> recetteList) {
        final List<String> stringList = new ArrayList<String>();
        for (Recette recette : recetteList)
            stringList.add(recette.getName());

        return stringList;
    }
}