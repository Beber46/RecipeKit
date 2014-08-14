package fr.beber.recipekit;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import fr.beber.bdd.dao.RecetteDAO;
import fr.beber.bean.Composition;
import fr.beber.bean.Recette;

/**
 * Cette classe permet d'afficher une préview de la recette
 *
 * @author Beber046
 * @version 1.0
 */
public class RecettePreviewActivity extends Activity {
    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recette_preview);

        final Recette recette = this.getRecetteCookie();

        final TextView nomRecette = (TextView) findViewById(R.id.textViewNomRecette);
        nomRecette.setText(recette.getName());

        final TextView tempsPreparation = (TextView) findViewById(R.id.textViewPreparation);
        tempsPreparation.setText("Temps de préparation : " + recette.getTempsPreparation().toString());

        final TextView tempsCuisson = (TextView) findViewById(R.id.textViewCuisson);
        tempsCuisson.setText("Temps de cuisson : " + recette.getTempsCuisson().toString());

        /*final GenericBaseAdapter stableArrayAdapter = new GenericBaseAdapter(this,
                android.R.layout.simple_list_item_1, this.constructionListeRecette(recette));
        final ListView listView = (ListView) findViewById(R.id.listViewProduit);
        listView.setAdapter(stableArrayAdapter);*/

        final TextView listeProduit = (TextView) findViewById(R.id.textViewProduit);
        listeProduit.setText(this.constructionListeRecette(recette));
    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            final ActionBar actionBar = getActionBar();
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        return super.onCreateOptionsMenu(menu);
    }

    /**
     * Permet de récupérer la recette du cookie.
     *
     * @return La recette de cookie.
     * @deprecated
     */
    private Recette getRecetteCookie() {
        final RecetteDAO recetteDAO = new RecetteDAO(this);
        recetteDAO.openOnlyRead();
        final Recette recette = recetteDAO.getAll().get(0);
        recetteDAO.close();
        return recette;
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
     * Permet de construire une liste de string à partir des produits de la recettes.
     *
     * @param recette à partir de laquelle on compose la liste de produits.
     * @return Liste de string (produit + quantité).
     */
    /*private List<String> constructionListeRecette(final Recette recette) {
        final List<String> stringList = new ArrayList<String>();
        for (Composition composition : recette.getCompositionList()) {
            Log.d(this.getClass().getName(), " Composition ( " + composition.getId() + " ) :" + composition);
            final String abreviation = composition.getUnit() != null ? composition.getUnit().getAbreviation() + " " : "";
            final String quantite = composition.getQuantite().compareTo(new Float(0)) > 0 ? composition.getQuantite() + " " : "";
            stringList.add(quantite + abreviation + composition.getProduit().getName());
        }
        return stringList;
    }*/
    private String constructionListeRecette(final Recette recette) {
        String retour = "- ";
        for (Composition composition : recette.getCompositionList()) {
            Log.d(this.getClass().getName(), " Composition ( " + composition.getId() + " ) :" + composition);
            final String abreviation = composition.getUnit() != null ? composition.getUnit().getAbreviation() + " " : "";
            final String quantite = composition.getQuantite().compareTo(new Float(0)) > 0 ? composition.getQuantite() + " " : "";
            retour = retour + quantite + abreviation + composition.getProduit().getName() + "\n- ";
        }
        return retour;
    }
}