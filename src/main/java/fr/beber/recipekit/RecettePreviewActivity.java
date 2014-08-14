package fr.beber.recipekit;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;
import fr.beber.bdd.dao.RecetteDAO;
import fr.beber.bean.Composition;
import fr.beber.bean.Recette;
import fr.beber.util.Constantes;

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
        getActionBar().setDisplayHomeAsUpEnabled(true);
        final Recette recette = this.getRecetteCookie();

        final TextView nomRecette = (TextView) findViewById(R.id.textViewNomRecette);
        nomRecette.setText(recette.getName());

        final TextView tempsPreparation = (TextView) findViewById(R.id.textViewTempsPreparation);
        tempsPreparation.setText("Temps de préparation : " + recette.getTempsPreparation().toString());

        final TextView tempsCuisson = (TextView) findViewById(R.id.textViewTempsCuisson);
        tempsCuisson.setText("Temps de cuisson : " + recette.getTempsCuisson().toString());

        final TextView listeProduit = (TextView) findViewById(R.id.textViewProduit);
        listeProduit.setText("Ingrédients : " + Constantes.DOUBLE_LINE_SEPARATOR + this.constructionListeRecette(recette));

        final TextView preparation = (TextView) findViewById(R.id.textViewPreparation);
        preparation.setText("Préparation : " + Constantes.DOUBLE_LINE_SEPARATOR + recette.getPreparation());
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
     * @return Texte (produit + quantité).
     */
    private String constructionListeRecette(final Recette recette) {
        String retour = "";
        for (Composition composition : recette.getCompositionList()) {
            retour = retour + "- ";
            Log.d(this.getClass().getName(), " Composition ( " + composition.getId() + " ) :" + composition);
            final String abreviation = composition.getUnit() != null ? composition.getUnit().getAbreviation() + " " : "";
            final String quantite = composition.getQuantite().compareTo(new Float(0)) > 0 ? composition.getQuantite() + " " : "";
            retour = retour + quantite + abreviation + composition.getProduit().getName() + Constantes.LINE_SEPARATOR;
        }
        return retour;
    }
}