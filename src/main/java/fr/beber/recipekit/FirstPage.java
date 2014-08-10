package fr.beber.recipekit;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import fr.beber.adapter.StableArrayAdapter;
import fr.beber.bdd.InitBD;
import fr.beber.bdd.dao.RecetteDAO;
import fr.beber.bean.Recette;

import java.util.ArrayList;
import java.util.List;

/**
 * Cette classe permet de
 * <p/>
 * @author Beber46
 * @date 09/08/2014
 * @version 1.0
 */
public class FirstPage extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        final InitBD initBD = new InitBD();

        final RecetteDAO recetteDAO = new RecetteDAO(this);
        recetteDAO.Open();
        if(recetteDAO.getAll().size()<1) {
            initBD.createRecetteMike(this);
            recetteDAO.Open();
        }
        final StableArrayAdapter stableArrayAdapter = new StableArrayAdapter(this,
                android.R.layout.simple_list_item_1, this.constructionListeRecette(recetteDAO.getAll()));

        final ListView listview = (ListView) findViewById(R.id.listeViewRecette);
        recetteDAO.Close();

        listview.setAdapter(stableArrayAdapter);
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
                onBackPressed();
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
    private List<String> constructionListeRecette(final List<Recette> recetteList){
        final List<String> stringList = new ArrayList<String>();
        for(Recette recette : recetteList)
            stringList.add(recette.getName());

        return stringList;
    }
}