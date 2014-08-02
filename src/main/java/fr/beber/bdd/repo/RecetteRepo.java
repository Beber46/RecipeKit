package fr.beber.bdd.repo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import fr.beber.bdd.BDD;
import fr.beber.bdd.Repository;
import fr.beber.object.Recette;

import java.util.ArrayList;
import java.util.List;

public class RecetteRepo extends Repository<Recette> {
	
	private static final String TAG = "RecetteRepo";
	
	private String[] mColumn = new String[]{
			BDD.PRODUIT_COLUMN_ID,
			BDD.PRODUIT_COLUMN_NAME
	};

	public RecetteRepo(Context ctx){
		mSQLOH = new BDD(ctx, null);
	}

	/**
	 * R�cup�ration des la liste des Recette
	 */
	@Override
	public List<Recette> GetAll() {
		Cursor c = mBDD.query(BDD.TN_RECETTE, mColumn , null, null, null, null, null);
		
		return ConvertCursorToListObject(c);
	}

	@Override
	public Recette GetById(Integer id) {
		Cursor c = mBDD.query(BDD.TN_RECETTE, mColumn , String.valueOf(id), null, null, null, null);
		
		return ConvertCursorToObject(c);
	}
	
	/**
	 * Enregistre un recette
	 */
	@Override
	public void Save(Recette entite) {
		Log.d(TAG, "Entree Save");
		ContentValues contentValues = new ContentValues();

		contentValues.put(mColumn[1], entite.getName());
		
		mBDD.insert(BDD.TN_RECETTE, null, contentValues);
		Log.d(TAG, "Sortie Save");
	}

	/**
	 * Met � jour le recette
	 */
	@Override
	public void Update(Recette entite) {
		Log.d(TAG, "Entree Update");
		ContentValues contentValues = new ContentValues();

		contentValues.put(mColumn[1], entite.getName());
		
		mBDD.update(BDD.TN_RECETTE, contentValues, mColumn[0] + "=?", new String[]{String.valueOf(entite.getId())});
		Log.d(TAG, "Sortie Update");
	}

	/**
	 * Supprimer un recette
	 */
	@Override
	public void Delete(Integer id) {
		Log.d(TAG, "Entree Delete");
		mBDD.delete(BDD.TN_RECETTE, mColumn[0] + "=?", new String[]{String.valueOf(id)});
		Log.d(TAG, "Sortie Delete");
	}

	/**
	 * Converti un curseur en une liste de recettes
	 */
	@Override
	public List<Recette> ConvertCursorToListObject(Cursor c) {
		List<Recette> liste = new ArrayList<Recette>();
		// Si la liste est vide
		if (c.getCount() == 0)
			return liste;
		// position sur le premeir item
		c.moveToFirst();
		// Pour chaque item
		do {
			Recette exec = ConvertCursorToObject(c);
			liste.add(exec);
		} while (c.moveToNext());
		// Fermeture du curseur
		c.close();

		return liste;
	}

	/**
	 * M�thode utilis�e par ConvertCursorToObject et ConvertCursorToListObject
	 */
	@Override
	public Recette ConvertCursorToObject(Cursor c) {
		
		Recette exec = new Recette();
		
		exec.setId(c.getInt(BDD.PRODUIT_NUM_ID));
		exec.setName(c.getString(BDD.PRODUIT_NUM_NAME));
		
		return exec;
	}
	
	/**
	 * Converti un curseur en un recette
	 */
	@Override
	public Recette ConvertCursorToOneObject(Cursor c) {
		c.moveToFirst();
		
		Recette exec = ConvertCursorToObject(c);
		
		c.close();
		return exec;
	}

}
