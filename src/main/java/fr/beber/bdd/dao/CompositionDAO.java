package fr.beber.bdd.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import fr.beber.bdd.BDD;
import fr.beber.bdd.Repository;
import fr.beber.bean.Composition;

import java.util.List;

/**
 * Cette classe permet de faire toutes les opérations en base pour l'objet {@link Composition}.
 *
 * @author Beber46
 * @version 1.0
 */
public class CompositionDAO extends Repository<Composition> {

    /**
     * Tag à utiliser pour le LOG
     */
	private static final String TAG = "CompositionRepo";

    /**
     * Champs en base de données de {@link Composition}
     */
	private final String[] mColumn = new String[]{
			BDD.COMPOSITION_COLUMN_ID,
			BDD.COMPOSITION_COLUMN_ID_PRODUIT,
			BDD.COMPOSITION_COLUMN_ID_RECETTE,
			BDD.COMPOSITION_COLUMN_QUANTITE,
			BDD.COMPOSITION_COLUMN_ID_UNIT
	};

    /**
     * Constructeur
     * @param context Définit le contexte à utiliser.
     */
	public CompositionDAO(final Context context){
		mSQLOH = new BDD(context, null);
	}

    /**
     * Permet de récupérer la liste des Compositions.
     *
     * @return La liste des compositions trouvée.
     */
	@Override
	public List<Composition> GetAll() {
        Log.d(TAG, "Entree");
		final Cursor c = mBDD.query(BDD.TN_COMPOSITION, mColumn , null, null, null, null, null);

        Log.d(TAG, "Sortie");
		return ConvertCursorToListObject(c);
	}

    /**
     * Permet de récupérer une compositino en fonction de son identifiant <code>id</code>.
     *
     * @param id Identifiant d'une composition.
     * @return La composition trouvé.
     */
	@Override
	public Composition GetById(final Integer id) {
        Log.d(TAG, "Entree");
		final Cursor c = mBDD.query(BDD.TN_COMPOSITION, mColumn , String.valueOf(id), null, null, null, null);

        Log.d(TAG, "Sortie");
		return ConvertCursorToObject(c);
	}

    /**
     * Permet d'enregistrer une composition.
     *
     * @param composition à enregistrer.
     */
	@Override
	public void Save(final Composition composition) {
		Log.d(TAG, "Entree");

        final ContentValues contentValues = this.getContentValues(composition);
		
		mBDD.insert(BDD.TN_COMPOSITION, null, contentValues);
		Log.d(TAG, "Sortie");
	}

    /**
     * Permet de mettre à jour une composition.
     *
     * @param composition à mettre à jour.
     */
	@Override
	public void Update(final Composition composition) {
		Log.d(TAG, "Entree");
		final ContentValues contentValues = this.getContentValues(composition);
		
		mBDD.update(BDD.TN_COMPOSITION, contentValues, mColumn[0] + "=?", new String[]{String.valueOf(composition.getId())});
		Log.d(TAG, "Sortie");
	}

    /**
     * Permet de créer un ContentValues à partir d'une composition.
     *
     * @param composition à convertir.
     * @return Le contentValues obtenu.
     */
    private ContentValues getContentValues(final Composition composition){
        final ContentValues contentValues = new ContentValues();

        contentValues.put(mColumn[1], composition.getIdProduit());
        contentValues.put(mColumn[2], composition.getIdRecette());
        contentValues.put(mColumn[3], composition.getQuantite());
        contentValues.put(mColumn[4], composition.getIdUnit());

        return contentValues;
    }

    /**
     * Permet de supprimer une composition en fonction de son identifiant.
     *
     * @param id Identifiant d'une composition.
     */
	@Override
	public void Delete(final Integer id) {
		Log.d(TAG, "Entree");
		mBDD.delete(BDD.TN_COMPOSITION, mColumn[0] + "=?", new String[]{String.valueOf(id)});
		Log.d(TAG, "Sortie");
	}

    /**
     * Permet de définir une {@link fr.beber.bean.Composition} à partir du {@link android.database.Cursor}.
     *
     * @param cursor à convertir.
     * @return Une compositiion.
     */
	@Override
	public Composition ConvertCursorToObject(final Cursor cursor) {
		
		final Composition exec = new Composition();
		
		exec.setId(cursor.getInt(BDD.COMPOSITION_NUM_ID));
		exec.setIdProduit(cursor.getInt(BDD.COMPOSITION_NUM_ID_PRODUIT));
		exec.setIdRecette(cursor.getInt(BDD.COMPOSITION_NUM_ID_RECETTE));
		exec.setQuantite(cursor.getInt(BDD.COMPOSITION_NUM_QUANTITE));
		exec.setIdUnit(cursor.getInt(BDD.COMPOSITION_NUM_ID_UNIT));

		return exec;
	}

}
