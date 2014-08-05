package fr.beber.bdd;

import android.content.Context;
import android.util.Log;
import fr.beber.bdd.dao.CompositionDAO;
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
 * Cette classe permet de
 *
 * @author Bertrand
 * @version 1.0
 * @date 04/08/2014
 */
public class InitBD {

    private static final String TAG = "InitBD";

    public InitBD(final Context context) {
        final List<Composition> compositionList = this.creationComposition(context);
        Log.d(TAG," Nombre de Composition créé: "+compositionList.size());
    }

    /**
     * Permet de créer la recette du cookie de mike.
     *
     * @param context
     * @return Recette créée.
     */
    private Recette creationRecette(final Context context){
        Recette recette = new Recette();
        recette.setName("Cookie Mike");
        recette.setPreparation("Mélangez la farine, les sucres, le sel, et la levure dans un grand saladier.\n" +
                "Faites fondre le beurre, et ajoutez-y, l'œuf battu et les 2 cuillères de miel, et incorporez le tout à la préparation.\n" +
                "Ajouter les pépites de chocolat (de préférence au lait, mais j'ai déjà goûté des cookies aux 3 chocolats, et c'est exquis), et mélanger avec une cuillère en bois.\n" +
                "Préchauffez votre four à 220°C (thermostat 7-8), avec la grille au plus bas.\n" +
                "Façonnez des cookies d’environ 10 cm de diamètre, et disposez-les sur une plaque. Ils doivent être assez espacés.\n" +
                "Enfournez-les 9 à 11 min, suivant si vous les souhaitez respectivement « extra-moelleux, moelleux ou crousti-moelleux »... Vous m’en direz des nouvelles!");
        recette.setTempsPreparation(10);
        recette.setTempsCuisson(15);

        Log.d(TAG,recette.toString());
        final RecetteDAO recetteDAO = new RecetteDAO(context);
        recetteDAO.Open();
        recetteDAO.save(recette);
        recetteDAO.Close();

        recetteDAO.Open();
        recette = recetteDAO.getAll().get(0);
        recetteDAO.Close();

        return recette;
    }

    /**
     * Créé la liste de produit pour la recette du cookie.
     *
     * @param context
     * @return Liste de produits pour la recette du cookie.
     */
    private List<Produit> creationListeProduit(final Context context){

        Produit produit = new Produit();
        produit.setName("Farine");

        final ProduitDAO produitDAO = new ProduitDAO(context);
        produitDAO.Open();
        produitDAO.save(produit);
        produitDAO.Close();
        

        produit = new Produit();
        produit.setName("Cassonade");
        produitDAO.Open();
        produitDAO.save(produit);
        produitDAO.Close();
        

        produit = new Produit();
        produit.setName("Sucre Vanillé");
        produitDAO.Open();
        produitDAO.save(produit);
        produitDAO.Close();
        

        produit = new Produit();
        produit.setName("Sel");
        produitDAO.Open();
        produitDAO.save(produit);
        produitDAO.Close();
        

        produit = new Produit();
        produit.setName("Levure");
        produitDAO.Open();
        produitDAO.save(produit);
        produitDAO.Close();
        

        produit = new Produit();
        produit.setName("Oeuf");
        produitDAO.Open();
        produitDAO.save(produit);
        produitDAO.Close();
        

        produit = new Produit();
        produit.setName("Beure");
        produitDAO.Open();
        produitDAO.save(produit);
        produitDAO.Close();
        

        produit = new Produit();
        produit.setName("Miel");
        produitDAO.Open();
        produitDAO.save(produit);
        produitDAO.Close();
        

        produit = new Produit();
        produit.setName("Pépites de Chocolat");
        produitDAO.Open();
        produitDAO.save(produit);
        produitDAO.Close();
        

        produitDAO.Open();
        final List<Produit> produitList = produitDAO.getAll();
        produitDAO.Close();
        
        return produitList;
    }

    /**
     * Créé les unités pour les produits de la recette.
     *
     * @param context
     * @return Liste d'unité pour la recette des cookies.
     */
    private List<Unit> creationUnite(final Context context){

        Unit unit = new Unit();
        unit.setName("Gramme");
        unit.setAbreviation("g");

        final UnitDAO unitDAO = new UnitDAO(context);
        unitDAO.Open();
        unitDAO.save(unit);
        unitDAO.Close();

        unitDAO.Open();
        unit = new Unit();
        unit.setName("Sachet");
        unitDAO.save(unit);
        unitDAO.Close();

        unitDAO.Open();
        unit = new Unit();
        unit.setName("Pincée");
        unitDAO.save(unit);
        unitDAO.Close();

        unitDAO.Open();
        unit = new Unit();
        unit.setName("Cuillère à café");
        unitDAO.save(unit);
        unitDAO.Close();

        unitDAO.Open();
        final List<Unit> unitList = unitDAO.getAll();
        unitDAO.Close();

        return unitList;
    }

    /**
     * Création des compositions pour la recette.
     * @param context
     * @return Liste créée.
     */
    private List<Composition> creationComposition(final Context context){

        final Recette recette = this.creationRecette(context);
        final List<Unit> unitList =  this.creationUnite(context);
        final List<Produit> produitList =  this.creationListeProduit(context);

        Composition composition = new Composition();
        final CompositionDAO compositionDAO = new CompositionDAO(context);

        composition.setIdRecette(recette.getId());
        composition.setProduit(produitList.get(0));//farine
        composition.setIdUnit(unitList.get(0).getId());//g
        composition.setQuantite(new Float(250));
        compositionDAO.Open();
        compositionDAO.save(composition);
        compositionDAO.Close();

        composition = new Composition();
        composition.setIdRecette(recette.getId());
        composition.setProduit(produitList.get(1));//sucre
        composition.setIdUnit(unitList.get(0).getId());//g
        composition.setQuantite(new Float(150));
        compositionDAO.Open();
        compositionDAO.save(composition);
        compositionDAO.Close();

        composition = new Composition();
        composition.setIdRecette(recette.getId());
        composition.setProduit(produitList.get(2));//sucre vanillé
        composition.setIdUnit(unitList.get(1).getId());//sachet
        composition.setQuantite(new Float(0.5));
        compositionDAO.Open();
        compositionDAO.save(composition);
        compositionDAO.Close();

        composition = new Composition();
        composition.setIdRecette(recette.getId());
        composition.setProduit(produitList.get(3));//sel
        composition.setIdUnit(unitList.get(2).getId());//pincé
        composition.setQuantite(new Float(1));
        compositionDAO.Open();
        compositionDAO.save(composition);
        compositionDAO.Close();

        composition = new Composition();
        composition.setIdRecette(recette.getId());
        composition.setProduit(produitList.get(4));//levure
        composition.setIdUnit(unitList.get(1).getId());//sachet
        composition.setQuantite(new Float(1));
        compositionDAO.Open();
        compositionDAO.save(composition);
        compositionDAO.Close();

        composition = new Composition();
        composition.setIdRecette(recette.getId());
        composition.setProduit(produitList.get(5));//oeuf
        composition.setQuantite(new Float(1));
        compositionDAO.Open();
        compositionDAO.save(composition);
        compositionDAO.Close();

        composition = new Composition();
        composition.setIdRecette(recette.getId());
        composition.setProduit(produitList.get(6));//beurre
        composition.setIdUnit(unitList.get(0).getId());//g
        composition.setQuantite(new Float(125));
        compositionDAO.Open();
        compositionDAO.save(composition);
        compositionDAO.Close();

        composition = new Composition();
        composition.setIdRecette(recette.getId());
        composition.setProduit(produitList.get(7));//beurre
        composition.setIdUnit(unitList.get(3).getId());//cuillère à café
        composition.setQuantite(new Float(2));
        compositionDAO.Open();
        compositionDAO.save(composition);
        compositionDAO.Close();

        composition = new Composition();
        composition.setIdRecette(recette.getId());
        composition.setProduit(produitList.get(8));//pepite chocolat
        compositionDAO.Open();
        compositionDAO.save(composition);
        compositionDAO.Close();

        compositionDAO.Open();
        final List<Composition> compositionList = compositionDAO.getAll();
        compositionDAO.Close();

        return compositionList;
    }
}
