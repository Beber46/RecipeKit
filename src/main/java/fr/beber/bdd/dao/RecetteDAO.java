package fr.beber.bdd.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import fr.beber.bdd.BDD;
import fr.beber.bdd.Repository;
import fr.beber.object.Recette;

import java.util.List;

/**
 * Cette classe permet de faire toutes les opérations en base pour l'objet {@link Recette}.
 *
 * @author Beber46
 * @version 1.0
 */
public class RecetteDAO extends Repository<Recette> {

    /**
     * Tag à utiliser pour le LOG
     */
	private static final String TAG = "RecetteRepo";

    /**
     * Champs en base de données de {@link Recette}
     */
	private String[] mColumn = new String[]{
			BDD.RECETTE_COLUMN_ID,
			BDD.RECETTE_COLUMN_NAME,
			BDD.RECETTE_COLUMN_PREPARATION,
			BDD.RECETTE_COLUMN_TEMPS_PREPARATION,
			BDD.RECETTE_COLUMN_TEMPS_CUISSON
	};

    /**
     * Constructeur
     * @param context Définit le contexte à utiliser.
     */
	public RecetteDAO(final Context context){
		mSQLOH = new BDD(context, null);
	}

    /**
     * Permet de récupérer la liste des recettes.
     *
     * @return La liste des recettes trouvée.
     */
	@Override
	public List<Recette> GetAll() {
        Log.d(TAG, "Entree");
		final Cursor cursor = mBDD.query(BDD.TN_RECETTE, mColumn , null, null, null, null, null);

        Log.d(TAG, "Sortie");
		return ConvertCursorToListObject(cursor);
	}

    /**
     * Permet de récupérer une recette en fonction de son identifiant <code>id</code>.
     *
     * @param id Identifiant d'une recette.
     * @return La recette trouvé.
     */
	@Override
	public Recette GetById(final Integer id) {
        Log.d(TAG, "Entree");
		final Cursor cursor = mBDD.query(BDD.TN_RECETTE, mColumn , String.valueOf(id), null, null, null, null);

        Log.d(TAG, "Sortie");
		return ConvertCursorToObject(cursor);
	}

    /**
     * Permet d'enregistrer une recette.
     *
     * @param recette à enregistrer.
     */
	@Override
	public void Save(final Recette recette) {
		Log.d(TAG, "Entree");
		final ContentValues contentValues = new ContentValues();

		contentValues.put(mColumn[1], recette.getName());
		
		mBDD.insert(BDD.TN_RECETTE, null, contentValues);
		Log.d(TAG, "Sortie");
	}

    /**
     * Permet de mettre à jour une recette.
     *
     * @param recette à mettre à jour.
     */
	@Override
	public void Update(final Recette recette) {
		Log.d(TAG, "Entree");
		final ContentValues contentValues = new ContentValues();

		contentValues.put(mColumn[1], recette.getName());
		
		mBDD.update(BDD.TN_RECETTE, contentValues, mColumn[0] + "=?", new String[]{String.valueOf(recette.getId())});
		Log.d(TAG, "Sortie");
	}

    /**
     * Permet de supprimer une recette en fonction de son identifiant.
     *
     * @param id Identifiant d'une recette.
     */
	@Override
	public void Delete(final Integer id) {
		Log.d(TAG, "Entree Delete");
		mBDD.delete(BDD.TN_RECETTE, mColumn[0] + "=?", new String[]{String.valueOf(id)});
		Log.d(TAG, "Sortie Delete");
	}

    /**
     * Permet de définir une {@link fr.beber.object.Recette} à partir du {@link android.database.Cursor}.
     *
     * @param cursor à convertir.
     * @return Une compositiion.
     */
	@Override
	public Recette ConvertCursorToObject(final Cursor cursor) {
		
		final Recette exec = new Recette();
		
		exec.setId(cursor.getInt(BDD.RECETTE_NUM_ID));
		exec.setName(cursor.getString(BDD.RECETTE_NUM_NAME));
		exec.setPreparation(cursor.getString(BDD.RECETTE_NUM_PREPARATION));
		exec.setTempsPreparation(cursor.getInt(BDD.RECETTE_NUM_TEMPS_PREPARATION));
		exec.setTempsCuisson(cursor.getInt(BDD.RECETTE_NUM_TEMPS_CUISSON));

		return exec;
	}
	
}
