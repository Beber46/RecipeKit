package fr.beber.bdd.repo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import fr.beber.bdd.BDD;
import fr.beber.bdd.Repository;
import fr.beber.object.Produit;

import java.util.ArrayList;
import java.util.List;

/**
 * Cette classe permet de faire toutes les opérations en base pour l'objet {@link Produit}.
 *
 * @author Beber46
 * @version 1.0
 */
public class ProduitRepo extends Repository<Produit> {

    /**
     * Tag à utiliser pour le LOG
     */
	private static final String TAG = "ProduitRepo";

    /**
     * Champs en base de données de {@link Produit}
     */
	private final String[] mColumn = new String[]{
			BDD.PRODUIT_COLUMN_ID,
			BDD.PRODUIT_COLUMN_NAME
	};

    /**
     * Constructeur
     * @param context
     */
	public ProduitRepo(final Context context){
		mSQLOH = new BDD(context, null);
	}

    /**
     * Permet de récupérer la liste des produits.
     *
     * @return La liste des produits trouvée.
     */
	@Override
	public List<Produit> GetAll() {
        Log.d(TAG, "Entree");
		final Cursor c = mBDD.query(BDD.TN_PRODUIT, mColumn , null, null, null, null, null);

        Log.d(TAG, "Sortie");
		return ConvertCursorToListObject(c);
	}

    /**
     * Permet de récupérer un produit en fonction de son identifiant <code>id</code>.
     *
     * @param id Identifiant d'un produit.
     * @return Le produit trouvé.
     */
	@Override
	public Produit GetById(final Integer id) {
        Log.d(TAG, "Entree");
		final Cursor c = mBDD.query(BDD.TN_PRODUIT, mColumn , String.valueOf(id), null, null, null, null);

        Log.d(TAG, "Sortie");
		return ConvertCursorToObject(c);
	}

    /**
     * Permet d'enregistrer un produit.
     *
     * @param produit à enregistrer.
     */
	@Override
	public void Save(final Produit produit) {
		Log.d(TAG, "Entree");
		final ContentValues contentValues = new ContentValues();

		contentValues.put(mColumn[1], produit.getName());
		
		mBDD.insert(BDD.TN_PRODUIT, null, contentValues);
		Log.d(TAG, "Sortie");
	}

    /**
     * Permet de mettre à jour un produit.
     *
     * @param produit à mettre à jour.
     */
	@Override
	public void Update(final Produit produit) {
		Log.d(TAG, "Entree");
		final ContentValues contentValues = new ContentValues();

		contentValues.put(mColumn[1], produit.getName());
		
		mBDD.update(BDD.TN_PRODUIT, contentValues, mColumn[0] + "=?", new String[]{String.valueOf(produit.getId())});
		Log.d(TAG, "Sortie");
	}

    /**
     * Permet de mettre à jour un produit.
     *
     * @param id Identifiant d'un produit.
     */
	@Override
	public void Delete(final Integer id) {
		Log.d(TAG, "Entree");
		mBDD.delete(BDD.TN_PRODUIT, mColumn[0] + "=?", new String[]{String.valueOf(id)});
		Log.d(TAG, "Sortie");
	}

    /**
     * Méthode utilisée par {@link #ConvertCursorToObject(android.database.Cursor)} et {@link #ConvertCursorToListObject(android.database.Cursor)}.
     *
     * @param cursor à convertir.
     * @return Un produit.
     */
	@Override
	public Produit ConvertCursorToObject(Cursor cursor) {
		
		Produit exec = new Produit();
		
		exec.setId(cursor.getInt(BDD.PRODUIT_NUM_ID));
		exec.setName(cursor.getString(BDD.PRODUIT_NUM_NAME));
		
		return exec;
	}
	
}
