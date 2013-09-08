package com.beber.bdd.repo;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.beber.bdd.BDD;
import com.beber.bdd.Repository;
import com.beber.object.Produit;

public class ProduitRepo extends Repository<Produit>{
	
	private static final String TAG = "ProduitRepo";
	
	private String[] mColumn = new String[]{
			BDD.PRODUIT_COLUMN_ID,
			BDD.PRODUIT_COLUMN_NAME
	};

	public ProduitRepo(Context ctx){
		mSQLOH = new BDD(ctx, null);
	}

	/**
	 * Récupération des la liste des Produit
	 */
	@Override
	public List<Produit> GetAll() {
		Cursor c = mBDD.query(BDD.TN_PRODUIT, mColumn , null, null, null, null, null);
		
		return ConvertCursorToListObject(c);
	}

	@Override
	public Produit GetById(int id) {
		Cursor c = mBDD.query(BDD.TN_PRODUIT, mColumn , String.valueOf(id), null, null, null, null);
		
		return ConvertCursorToObject(c);
	}
	
	/**
	 * Enregistre un produit
	 */
	@Override
	public void Save(Produit entite) {
		Log.d(TAG, "Entree Save");
		ContentValues contentValues = new ContentValues();

		contentValues.put(mColumn[1], entite.getName());
		
		mBDD.insert(BDD.TN_PRODUIT, null, contentValues);
		Log.d(TAG, "Sortie Save");
	}

	/**
	 * Met à jour le produit
	 */
	@Override
	public void Update(Produit entite) {
		Log.d(TAG, "Entree Update");
		ContentValues contentValues = new ContentValues();

		contentValues.put(mColumn[1], entite.getName());
		
		mBDD.update(BDD.TN_PRODUIT, contentValues, mColumn[0] + "=?", new String[]{String.valueOf(entite.getId())});
		Log.d(TAG, "Sortie Update");
	}

	/**
	 * Supprimer un produit
	 */
	@Override
	public void Delete(int id) {
		Log.d(TAG, "Entree Delete");
		mBDD.delete(BDD.TN_PRODUIT, mColumn[0] + "=?", new String[]{String.valueOf(id)});
		Log.d(TAG, "Sortie Delete");
	}

	/**
	 * Converti un curseur en une liste de produits
	 */
	@Override
	public List<Produit> ConvertCursorToListObject(Cursor c) {
		List<Produit> liste = new ArrayList<Produit>();
		// Si la liste est vide
		if (c.getCount() == 0)
			return liste;
		// position sur le premeir item
		c.moveToFirst();
		// Pour chaque item
		do {
			Produit exec = ConvertCursorToObject(c);
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
	public Produit ConvertCursorToObject(Cursor c) {
		
		Produit exec = new Produit();
		
		exec.setId(c.getInt(BDD.PRODUIT_NUM_ID));
		exec.setName(c.getString(BDD.PRODUIT_NUM_NAME));
		
		return exec;
	}
	
	/**
	 * Converti un curseur en un produit
	 */
	@Override
	public Produit ConvertCursorToOneObject(Cursor c) {
		c.moveToFirst();
		
		Produit exec = ConvertCursorToObject(c);
		
		c.close();
		return exec;
	}

}
