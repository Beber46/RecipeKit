package com.beber.bdd.repo;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.beber.bdd.BDD;
import com.beber.bdd.Repository;
import com.beber.object.Unit;

public class UnitRepo extends Repository<Unit>{
	
	private static final String TAG = "UnitRepo";
	
	private String[] mColumn = new String[]{
			BDD.PRODUIT_COLUMN_ID,
			BDD.PRODUIT_COLUMN_NAME
	};

	public UnitRepo(Context ctx){
		mSQLOH = new BDD(ctx, null);
	}

	/**
	 * Récupération des la liste des Unit
	 */
	@Override
	public List<Unit> GetAll() {
		Cursor c = mBDD.query(BDD.TN_UNIT, mColumn , null, null, null, null, null);
		
		return ConvertCursorToListObject(c);
	}

	@Override
	public Unit GetById(int id) {
		Cursor c = mBDD.query(BDD.TN_UNIT, mColumn , String.valueOf(id), null, null, null, null);
		
		return ConvertCursorToObject(c);
	}
	
	/**
	 * Enregistre un unit
	 */
	@Override
	public void Save(Unit entite) {
		Log.d(TAG, "Entree Save");
		ContentValues contentValues = new ContentValues();

		contentValues.put(mColumn[1], entite.getName());
		
		mBDD.insert(BDD.TN_UNIT, null, contentValues);
		Log.d(TAG, "Sortie Save");
	}

	/**
	 * Met à jour le unit
	 */
	@Override
	public void Update(Unit entite) {
		Log.d(TAG, "Entree Update");
		ContentValues contentValues = new ContentValues();

		contentValues.put(mColumn[1], entite.getName());
		
		mBDD.update(BDD.TN_UNIT, contentValues, mColumn[0] + "=?", new String[]{String.valueOf(entite.getId())});
		Log.d(TAG, "Sortie Update");
	}

	/**
	 * Supprimer un unit
	 */
	@Override
	public void Delete(int id) {
		Log.d(TAG, "Entree Delete");
		mBDD.delete(BDD.TN_UNIT, mColumn[0] + "=?", new String[]{String.valueOf(id)});
		Log.d(TAG, "Sortie Delete");
	}

	/**
	 * Converti un curseur en une liste de units
	 */
	@Override
	public List<Unit> ConvertCursorToListObject(Cursor c) {
		List<Unit> liste = new ArrayList<Unit>();
		// Si la liste est vide
		if (c.getCount() == 0)
			return liste;
		// position sur le premeir item
		c.moveToFirst();
		// Pour chaque item
		do {
			Unit exec = ConvertCursorToObject(c);
			liste.add(exec);
		} while (c.moveToNext());
		// Fermeture du curseur
		c.close();

		return liste;
	}

	/**
	 * Méthode utilisée par ConvertCursorToObject et ConvertCursorToListObject
	 */
	@Override
	public Unit ConvertCursorToObject(Cursor c) {
		
		Unit exec = new Unit();
		
		exec.setId(c.getInt(BDD.PRODUIT_NUM_ID));
		exec.setName(c.getString(BDD.PRODUIT_NUM_NAME));
		
		return exec;
	}
	
	/**
	 * Converti un curseur en un unit
	 */
	@Override
	public Unit ConvertCursorToOneObject(Cursor c) {
		c.moveToFirst();
		
		Unit exec = ConvertCursorToObject(c);
		
		c.close();
		return exec;
	}

}
