package fr.beber.bdd.repo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import fr.beber.bdd.BDD;
import fr.beber.bdd.Repository;
import fr.beber.object.Unit;

import java.util.List;

/**
 * Cette classe permet de faire toutes les opérations en base pour l'objet {@link Unit}.
 *
 * @author Beber46
 * @version 1.0
 */
public class UnitRepo extends Repository<Unit>{

    /**
     * Tag à utiliser pour le LOG
     */
	private static final String TAG = "UnitRepo";

    /**
     * Champs en base de données de {@link Unit}
     */
	private String[] mColumn = new String[]{
			BDD.UNIT_COLUMN_ID,
			BDD.UNIT_COLUMN_NAME
	};

    /**
     * Constructeur
     * @param context Définit le contexte à utiliser.
     */
	public UnitRepo(final Context context){
		mSQLOH = new BDD(context, null);
	}

    /**
     * Permet de récupérer la liste d'unité.
     *
     * @return La liste des compositions trouvée.
     */
	@Override
	public List<Unit> GetAll() {
        Log.d(TAG, "Entree");
		final Cursor cursor = mBDD.query(BDD.TN_UNIT, mColumn , null, null, null, null, null);

        Log.d(TAG, "Sortie");
		return ConvertCursorToListObject(cursor);
	}

    /**
     * Permet de récupérer une unité en fonction de son identifiant <code>id</code>.
     *
     * @param id Identifiant d'une unité.
     * @return L'unité trouvé.
     */
	@Override
	public Unit GetById(final Integer id) {
        Log.d(TAG, "Entree");
		final Cursor cursor = mBDD.query(BDD.TN_UNIT, mColumn , String.valueOf(id), null, null, null, null);

        Log.d(TAG, "Sortie");
		return ConvertCursorToObject(cursor);
	}

    /**
     * Permet d'enregistrer une unité.
     *
     * @param unit à enregistrer.
     */
	@Override
	public void Save(final Unit unit) {
		Log.d(TAG, "Entree");
		ContentValues contentValues = new ContentValues();

		contentValues.put(mColumn[1], unit.getName());
		
		mBDD.insert(BDD.TN_UNIT, null, contentValues);
		Log.d(TAG, "Sortie");
	}

    /**
     * Permet de mettre à jour une unité.
     *
     * @param unit à mettre à jour.
     */
	@Override
	public void Update(final Unit unit) {
		Log.d(TAG, "Entree");
		final ContentValues contentValues = new ContentValues();

		contentValues.put(mColumn[1], unit.getName());
		
		mBDD.update(BDD.TN_UNIT, contentValues, mColumn[0] + "=?", new String[]{String.valueOf(unit.getId())});
		Log.d(TAG, "Sortie");
	}

    /**
     * Permet de supprimer une unité.
     *
     * @param id Identifiant d'une unité.
     */
	@Override
	public void Delete(final Integer id) {
		Log.d(TAG, "Entree");
		mBDD.delete(BDD.TN_UNIT, mColumn[0] + "=?", new String[]{String.valueOf(id)});
		Log.d(TAG, "Sortie");
	}

    /**
     * Permet de définir une {@link fr.beber.object.Unit} à partir du {@link android.database.Cursor}.
     *
     * @param cursor à convertir.
     * @return Une unité.
     */
	@Override
	public Unit ConvertCursorToObject(final Cursor cursor) {
		
		final Unit exec = new Unit();
		exec.setId(cursor.getInt(BDD.UNIT_NUM_ID));
		exec.setName(cursor.getString(BDD.UNIT_NUM_NAME));
		
		return exec;
	}

}
