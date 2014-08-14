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

import java.util.List;

/**
 * Cette classe permet d'initialiser 1 recette.
 *
 * @author Beber046
 * @version 1.0
 */
public class InitBD {

    /**
     * Permet de créer la recette de mike.
     *
     * @param context Le contexte courant.
     */
    public void createRecetteMike(final Context context) {
        this.creationComposition(context);
        final RecetteDAO recetteDAO = new RecetteDAO(context);
        recetteDAO.open();
        final Recette recette = recetteDAO.getAll().get(0);
        recetteDAO.close();
        Log.d(this.getClass().getName(), " Nombre de Composition créé: " + recette.getCompositionList().size());
        Log.d(this.getClass().getName(), " Recette: " + recette.toString());

    }

    /**
     * Permet de créer la recette du cookie de mike.
     *
     * @param context Le contexte courant.
     * @return Recette créée.
     */
    private Recette creationRecette(final Context context) {
        final RecetteDAO recetteDAO = new RecetteDAO(context);
        recetteDAO.open();
        Recette recette = new Recette();
        recette.setName("Cookies");
        recette.setPreparation("Mélangez la farine, les sucres, le sel, et la levure dans un grand saladier.\n" +
                "Faites fondre le beurre, et ajoutez-y, l'œuf battu et les 2 cuillères de miel, et incorporez le tout à la préparation.\n" +
                "Ajouter les pépites de chocolat (de préférence au lait, mais j'ai déjà goûté des cookies aux 3 chocolats, et c'est exquis), et mélanger avec une cuillère en bois.\n" +
                "Préchauffez votre four à 220°C (thermostat 7-8), avec la grille au plus bas.\n" +
                "Façonnez des cookies d’environ 10 cm de diamètre, et disposez-les sur une plaque. Ils doivent être assez espacés.\n" +
                "Enfournez-les 9 à 11 min, suivant si vous les souhaitez respectivement « extra-moelleux, moelleux ou crousti-moelleux »... Vous m’en direz des nouvelles!");
        recette.setTempsPreparation(10);
        recette.setTempsCuisson(15);
        recette.setNote(new Float(4));
        recette.setNbPersonne(15);

        Log.d(this.getClass().getName(), recette.toString());
        recetteDAO.save(recette);

        recette = recetteDAO.getAll().get(0);
        recetteDAO.close();

        return recette;
    }

    /**
     * Créé la liste de produit pour la recette du cookie.
     *
     * @param context Le contexte courant.
     * @return Liste de produits pour la recette du cookie.
     */
    private List<Produit> creationListeProduit(final Context context) {

        final ProduitDAO produitDAO = new ProduitDAO(context);
        produitDAO.open();
        Produit produit = new Produit();
        produit.setName("Farine");

        produitDAO.save(produit);

        produit = new Produit();
        produit.setName("Cassonade");
        produitDAO.save(produit);

        produit = new Produit();
        produit.setName("Sucre Vanillé");
        produitDAO.save(produit);

        produit = new Produit();
        produit.setName("Sel");
        produitDAO.save(produit);

        produit = new Produit();
        produit.setName("Levure");
        produitDAO.save(produit);

        produit = new Produit();
        produit.setName("Oeuf");
        produitDAO.save(produit);

        produit = new Produit();
        produit.setName("Beure");
        produitDAO.save(produit);

        produit = new Produit();
        produit.setName("Miel");
        produitDAO.save(produit);

        produit = new Produit();
        produit.setName("Pépites de Chocolat");
        produitDAO.save(produit);


        final List<Produit> produitList = produitDAO.getAll();
        produitDAO.close();
        return produitList;
    }

    /**
     * Créé les unités pour les produits de la recette.
     *
     * @param context Le contexte courant.
     * @return Liste d'unité pour la recette des cookies.
     */
    private List<Unit> creationUnite(final Context context) {

        final UnitDAO unitDAO = new UnitDAO(context);
        unitDAO.open();
        Unit unit = new Unit();
        unit.setName("Gramme");
        unit.setAbreviation("g");

        unitDAO.save(unit);

        unit = new Unit();
        unit.setName("Sachet");
        unitDAO.save(unit);

        unit = new Unit();
        unit.setName("Pincée");
        unitDAO.save(unit);

        unit = new Unit();
        unit.setName("Cuillère à café");
        unitDAO.save(unit);

        final List<Unit> unitList = unitDAO.getAll();
        unitDAO.close();
        return unitList;
    }

    /**
     * Création des compositions pour la recette.
     *
     * @param context Le contexte courant.
     * @return Liste créée.
     */
    private void creationComposition(final Context context) {

        final Recette recette = this.creationRecette(context);
        final List<Unit> unitList = this.creationUnite(context);
        final List<Produit> produitList = this.creationListeProduit(context);

        Log.d(this.getClass().getName(), " recette : " + recette);
        Log.d(this.getClass().getName(), " unitList : " + unitList);
        Log.d(this.getClass().getName(), " produitList : " + produitList);

        Composition composition = new Composition();
        final CompositionDAO compositionDAO = new CompositionDAO(context);
        compositionDAO.open();

        composition.setIdRecette(recette.getId());
        composition.setProduit(produitList.get(0));//farine
        composition.setUnit(unitList.get(0));//g
        composition.setQuantite(new Float(250));
        compositionDAO.save(composition);

        composition = new Composition();
        composition.setIdRecette(recette.getId());
        composition.setProduit(produitList.get(1));//sucre
        composition.setUnit(unitList.get(0));//g
        composition.setQuantite(new Float(150));
        compositionDAO.save(composition);

        composition = new Composition();
        composition.setIdRecette(recette.getId());
        composition.setProduit(produitList.get(2));//sucre vanillé
        composition.setUnit(unitList.get(1));//sachet
        composition.setQuantite(new Float(0.5));
        compositionDAO.save(composition);

        composition = new Composition();
        composition.setIdRecette(recette.getId());
        composition.setProduit(produitList.get(3));//sel
        composition.setUnit(unitList.get(2));//pincé
        composition.setQuantite(new Float(1));
        compositionDAO.save(composition);

        composition = new Composition();
        composition.setIdRecette(recette.getId());
        composition.setProduit(produitList.get(4));//levure
        composition.setUnit(unitList.get(1));//sachet
        composition.setQuantite(new Float(1));
        compositionDAO.save(composition);

        composition = new Composition();
        composition.setIdRecette(recette.getId());
        composition.setProduit(produitList.get(5));//oeuf
        composition.setQuantite(new Float(1));
        compositionDAO.save(composition);

        composition = new Composition();
        composition.setIdRecette(recette.getId());
        composition.setProduit(produitList.get(6));//beurre
        composition.setUnit(unitList.get(0));//g
        composition.setQuantite(new Float(125));
        compositionDAO.save(composition);

        composition = new Composition();
        composition.setIdRecette(recette.getId());
        composition.setProduit(produitList.get(7));//beurre
        composition.setUnit(unitList.get(3));//cuillère à café
        composition.setQuantite(new Float(2));
        compositionDAO.save(composition);

        composition = new Composition();
        composition.setIdRecette(recette.getId());
        composition.setProduit(produitList.get(8));//pepite chocolat
        compositionDAO.save(composition);

        compositionDAO.close();
    }
}
