package fr.beber.recipekit;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;
import fr.beber.adapter.StableArrayAdapter;
import fr.beber.bdd.dao.ProduitDAO;
import fr.beber.bdd.dao.RecetteDAO;
import fr.beber.bdd.dao.UnitDAO;
import fr.beber.bean.Composition;
import fr.beber.bean.Produit;
import fr.beber.bean.Recette;
import fr.beber.bean.Unit;

import java.util.ArrayList;
import java.util.List;

/**
 * Cette classe permet d'afficher une préview de la recette
 *
 * @author Bertrand
 * @version 1.0
 * @date 10/08/2014
 */
public class RecettePreviewActivity extends Activity {
    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recette_preview);

        final Recette recette = this.getRecetteCookie();

        final TextView nomRecette = (TextView)findViewById(R.id.textViewNomRecette);
        nomRecette.setText(recette.getName());

        final TextView tempsPreparation = (TextView)findViewById(R.id.textViewPreparation);
        tempsPreparation.setText("Temps de préparation : "+recette.getTempsPreparation().toString());

        final TextView tempsCuisson = (TextView)findViewById(R.id.textViewCuisson);
        tempsCuisson.setText("Temps de cuisson : "+recette.getTempsCuisson().toString());

        /*final UnitDAO unitDAO = new UnitDAO(this);
        unitDAO.openOnlyRead();
        final List<Unit> unit = unitDAO.getAll();
        for(Unit u : unit) {
            Log.d(this.getClass().getName(), "u : " + u);
            Log.d(this.getClass().getName(), "u 2 : " + unitDAO.getById(u.getId()));
        }
        unitDAO.close();*/

        final StableArrayAdapter stableArrayAdapter = new StableArrayAdapter(this,
                android.R.layout.simple_list_item_1, this.constructionListeRecette(recette));
        final ListView listView = (ListView) findViewById(R.id.listViewProduit);
        listView.setAdapter(stableArrayAdapter);
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
    private Recette getRecetteCookie(){
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
     * @param recette à partir de laquelle on compose la liste de produits.
     * @return Liste de string (produit + quantité).
     */
    private List<String> constructionListeRecette(final Recette recette){
        final List<String> stringList = new ArrayList<String>();
        for(Composition composition : recette.getCompositionList()) {
            Log.d(this.getClass().getName()," Composition ( "+ composition.getId() + " ) :"+composition);
            stringList.add(composition.getQuantite()+" "+ this.getAbreviationUnitParId(composition.getIdUnit()) + " " + this.getNomProduitParId(composition.getIdProduit()));
        }
        return stringList;
    }

    /**
     * Permet de récupérer le nom d'un produit par son identifiant.
     * @param id Identifiant d'un produit.
     * @return Le nom du produit.
     */
    private String getNomProduitParId(final Integer id){
        final ProduitDAO produitDAO = new ProduitDAO(this);
        produitDAO.openOnlyRead();
        final Produit produit = produitDAO.getById(id);
        produitDAO.close();

        return produit.getName();
    }

    /**
     * Permet de récupérer l'abréviation d'une unité par son identifiant.
     * @param id Identifiant d'une unité.
     * @return L'abréviation du produit.
     */
    private String getAbreviationUnitParId(final Integer id){
        if(id.compareTo(new Integer(0))>0) {
            final UnitDAO unitDAO = new UnitDAO(this);
            unitDAO.openOnlyRead();
            final Unit unit = unitDAO.getById(id);
            unitDAO.close();
            return unit.getName();
        }
        return "";
    }
}