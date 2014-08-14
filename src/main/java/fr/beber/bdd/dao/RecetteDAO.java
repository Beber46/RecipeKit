package fr.beber.bdd.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import fr.beber.bdd.BDD;
import fr.beber.bdd.Repository;
import fr.beber.bean.Recette;

import java.util.List;

/**
 * Cette classe permet de faire toutes les opérations en base pour l'objet {@link Recette}.
 *
 * @author Beber46
 * @version 1.0
 */
public class RecetteDAO extends Repository<Recette> {

    /**
     * Champs en base de données de {@link Recette}
     */
    private String[] mColumn = new String[]{
            BDD.RECETTE_COLUMN_ID,
            BDD.RECETTE_COLUMN_NAME,
            BDD.RECETTE_COLUMN_PREPARATION,
            BDD.RECETTE_COLUMN_TEMPS_PREPARATION,
            BDD.RECETTE_COLUMN_TEMPS_CUISSON,
            BDD.RECETTE_COLUMN_NOTE,
            BDD.RECETTE_COLUMN_NB_PERSONNE
    };

    /**
     * Constructeur
     *
     * @param context Le contexte courant.
     */
    public RecetteDAO(final Context context) {
        mSQLOH = new BDD(context, null);
    }

    /**
     * Permet de récupérer la liste des recettes.
     *
     * @return La liste des recettes trouvée.
     */
    @Override
    public List<Recette> getAll() {
        Log.d(this.getClass().getName(), "Entree");
        final Cursor cursor = mBDD.query(BDD.TN_RECETTE, mColumn, null, null, null, null, null);

        Log.d(this.getClass().getName(), "Sortie");
        return convertCursorToListObject(cursor);
    }

    /**
     * Permet de récupérer une recette en fonction de son identifiant <code>id</code>.
     *
     * @param id Identifiant d'une recette.
     * @return La recette trouvé.
     */
    @Override
    public Recette getById(final Integer id) {
        Log.d(this.getClass().getName(), "Entree");
        final Cursor cursor = mBDD.query(BDD.TN_RECETTE, mColumn, String.valueOf(id), null, null, null, null);

        Log.d(this.getClass().getName(), "Sortie");
        return convertCursorToOneObject(cursor);
    }

    /**
     * Permet d'enregistrer une recette.
     *
     * @param recette à enregistrer.
     */
    @Override
    public void save(final Recette recette) {
        Log.d(this.getClass().getName(), "Entree");
        final ContentValues contentValues = new ContentValues();

        contentValues.put(mColumn[1], recette.getName());
        contentValues.put(mColumn[2], recette.getPreparation());
        contentValues.put(mColumn[3], recette.getTempsPreparation());
        if (recette.getTempsCuisson() != null)
            contentValues.put(mColumn[4], recette.getTempsCuisson());
        contentValues.put(mColumn[5], recette.getNote());
        contentValues.put(mColumn[6], recette.getNbPersonne());

        mBDD.insert(BDD.TN_RECETTE, null, contentValues);
        Log.d(this.getClass().getName(), "Sortie");
    }

    /**
     * Permet de mettre à jour une recette.
     *
     * @param recette à mettre à jour.
     */
    @Override
    public void update(final Recette recette) {
        Log.d(this.getClass().getName(), "Entree");
        final ContentValues contentValues = new ContentValues();

        contentValues.put(mColumn[1], recette.getName());

        mBDD.update(BDD.TN_RECETTE, contentValues, mColumn[0] + "=?", new String[]{String.valueOf(recette.getId())});
        Log.d(this.getClass().getName(), "Sortie");
    }

    /**
     * Permet de supprimer une recette en fonction de son identifiant.
     *
     * @param id Identifiant d'une recette.
     */
    @Override
    public void delete(final Integer id) {
        Log.d(this.getClass().getName(), "Entree");
        mBDD.delete(BDD.TN_RECETTE, mColumn[0] + "=?", new String[]{String.valueOf(id)});
        Log.d(this.getClass().getName(), "Sortie");
    }

    /**
     * Permet de définir une {@link fr.beber.bean.Recette} à partir du {@link android.database.Cursor}.
     *
     * @param cursor à convertir.
     * @return Une compositiion.
     */
    @Override
    public Recette convertCursorToObject(final Cursor cursor) {

        final Recette recette = new Recette();

        recette.setId(cursor.getInt(BDD.RECETTE_NUM_ID));
        recette.setName(cursor.getString(BDD.RECETTE_NUM_NAME));
        recette.setPreparation(cursor.getString(BDD.RECETTE_NUM_PREPARATION));
        recette.setTempsPreparation(cursor.getInt(BDD.RECETTE_NUM_TEMPS_PREPARATION));
        recette.setTempsCuisson(cursor.getInt(BDD.RECETTE_NUM_TEMPS_CUISSON));
        recette.setNote(cursor.getFloat(BDD.RECETTE_NUM_NOTE));
        recette.setNbPersonne(cursor.getInt(BDD.RECETTE_NUM_NB_PERSONNE));

        final CompositionDAO compositionDAO = new CompositionDAO(mSQLOH);
        recette.setCompositionList(compositionDAO.getByIdRecette(recette.getId()));

        return recette;
    }

}
