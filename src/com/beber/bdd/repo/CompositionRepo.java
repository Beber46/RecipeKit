package com.beber.bdd.repo;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.beber.bdd.BDD;
import com.beber.bdd.Repository;
import com.beber.object.Composition;

public class CompositionRepo  extends Repository<Composition>{
	
	private static final String TAG = "CompositionRepo";
	
	private String[] mColumn = new String[]{
			BDD.COMPOSITION_COLUMN_ID,
			BDD.COMPOSITION_COLUMN_ID_PRODUIT,
			BDD.COMPOSITION_COLUMN_ID_RECETTE,
			BDD.COMPOSITION_COLUMN_QUANTITE,
			BDD.COMPOSITION_COLUMN_ID_UNIT
	};

	public CompositionRepo(Context ctx){
		mSQLOH = new BDD(ctx, null);
	}

	/**
	 * Récupération des la liste des Composition
	 */
	@Override
	public List<Composition> GetAll() {
		Cursor c = mBDD.query(BDD.TN_COMPOSITION, mColumn , null, null, null, null, null);
		
		return ConvertCursorToListObject(c);
	}

	@Override
	public Composition GetById(int id) {
		Cursor c = mBDD.query(BDD.TN_COMPOSITION, mColumn , String.valueOf(id), null, null, null, null);
		
		return ConvertCursorToObject(c);
	}
	
	/**
	 * Enregistre un produit
	 */
	@Override
	public void Save(Composition entite) {
		Log.d(TAG, "Entree Save");
		
		ContentValues contentValues = new ContentValues();

		contentValues.put(mColumn[1], entite.getId_produit());
		contentValues.put(mColumn[2], entite.getId_recette());
		contentValues.put(mColumn[3], entite.getQuantite());
		contentValues.put(mColumn[4], entite.getId_unit());
		
		mBDD.insert(BDD.TN_COMPOSITION, null, contentValues);
		Log.d(TAG, "Sortie Save");
	}

	/**
	 * Met à jour le produit
	 */
	@Override
	public void Update(Composition entite) {
		Log.d(TAG, "Entree Update");
		ContentValues contentValues = new ContentValues();

		contentValues.put(mColumn[1], entite.getId_produit());
		contentValues.put(mColumn[2], entite.getId_recette());
		contentValues.put(mColumn[3], entite.getQuantite());
		contentValues.put(mColumn[4], entite.getId_unit());
		
		mBDD.update(BDD.TN_COMPOSITION, contentValues, mColumn[0] + "=?", new String[]{String.valueOf(entite.getId())});
		Log.d(TAG, "Sortie Update");
	}

	/**
	 * Supprimer un produit
	 */
	@Override
	public void Delete(int id) {
		Log.d(TAG, "Entree Delete");
		mBDD.delete(BDD.TN_COMPOSITION, mColumn[0] + "=?", new String[]{String.valueOf(id)});
		Log.d(TAG, "Sortie Delete");
	}

	/**
	 * Converti un curseur en une liste de produits
	 */
	@Override
	public List<Composition> ConvertCursorToListObject(Cursor c) {
		List<Composition> liste = new ArrayList<Composition>();
		// Si la liste est vide
		if (c.getCount() == 0)
			return liste;
		// position sur le premeir item
		c.moveToFirst();
		// Pour chaque item
		do {
			Composition exec = ConvertCursorToObject(c);
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
	public Composition ConvertCursorToObject(Cursor c) {
		
		Composition exec = new Composition();
		
		exec.setId(c.getInt(BDD.COMPOSITION_NUM_ID));
		exec.setId_produit(c.getInt(BDD.COMPOSITION_NUM_ID_PRODUIT));
		exec.setId_recette(c.getInt(BDD.COMPOSITION_NUM_ID_RECETTE));
		exec.setQuantite(c.getInt(BDD.COMPOSITION_NUM_QUANTITE));
		exec.setId_unit(c.getInt(BDD.COMPOSITION_NUM_ID_UNIT));
		
		return exec;
	}
	
	/**
	 * Converti un curseur en un produit
	 */
	@Override
	public Composition ConvertCursorToOneObject(Cursor c) {
		c.moveToFirst();
		
		Composition exec = ConvertCursorToObject(c);
		
		c.close();
		return exec;
	}
}
