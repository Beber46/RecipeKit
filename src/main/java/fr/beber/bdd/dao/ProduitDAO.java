package fr.beber.bdd.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import fr.beber.bdd.BDD;
import fr.beber.bdd.Repository;
import fr.beber.bean.Produit;

import java.util.List;

/**
 * Cette classe permet de faire toutes les opérations en base pour l'objet {@link Produit}.
 *
 * @author Beber46
 * @version 1.0
 */
public class ProduitDAO extends Repository<Produit> {

    /**
     * Champs en base de données de {@link Produit}
     */
	private final String[] mColumn = new String[]{
			BDD.PRODUIT_COLUMN_ID,
			BDD.PRODUIT_COLUMN_NAME
	};

    /**
     * Constructeur
     * @param context Définit le contexte à utiliser.
     */
	public ProduitDAO(final Context context){
		mSQLOH = new BDD(context, null);
	}

    public ProduitDAO(final SQLiteOpenHelper sqLiteOpenHelper){
        mSQLOH = sqLiteOpenHelper;
    }

    /**
     * Permet de récupérer la liste des produits.
     *
     * @return La liste des produits trouvée.
     */
	@Override
	public List<Produit> getAll() {
        Log.d(this.getClass().getName(), "Entree");
		final Cursor c = mBDD.query(BDD.TN_PRODUIT, mColumn , null, null, null, null, null);

        Log.d(this.getClass().getName(), "Sortie");
		return convertCursorToListObject(c);
	}

    /**
     * Permet de récupérer un produit en fonction de son identifiant <code>id</code>.
     *
     * @param id Identifiant d'un produit.
     * @return Le produit trouvé.
     */
	@Override
	public Produit getById(final Integer id) {
        Log.d(this.getClass().getName(), "Entree");
		final Cursor c = mBDD.query(BDD.TN_PRODUIT, mColumn ,  mColumn[0] + "=?", new String[]{String.valueOf(id)}, null, null, null);

        Log.d(this.getClass().getName(), "Sortie");
		return convertCursorToOneObject(c);
	}

    /**
     * Permet d'enregistrer un produit.
     *
     * @param produit à enregistrer.
     */
	@Override
	public void save(final Produit produit) {
		Log.d(this.getClass().getName(), "Entree");
		final ContentValues contentValues = new ContentValues();

		contentValues.put(mColumn[1], produit.getName());

		mBDD.insert(BDD.TN_PRODUIT, null, contentValues);
		Log.d(this.getClass().getName(), "Sortie");
	}

    /**
     * Permet de mettre à jour un produit.
     *
     * @param produit à mettre à jour.
     */
	@Override
	public void update(final Produit produit) {
		Log.d(this.getClass().getName(), "Entree");
		final ContentValues contentValues = new ContentValues();

		contentValues.put(mColumn[1], produit.getName());

		mBDD.update(BDD.TN_PRODUIT, contentValues, mColumn[0] + "=?", new String[]{String.valueOf(produit.getId())});
		Log.d(this.getClass().getName(), "Sortie");
	}

    /**
     * Permet de mettre à jour un produit.
     *
     * @param id Identifiant d'un produit.
     */
	@Override
	public void delete(final Integer id) {
		Log.d(this.getClass().getName(), "Entree");
		mBDD.delete(BDD.TN_PRODUIT, mColumn[0] + "=?", new String[]{String.valueOf(id)});
		Log.d(this.getClass().getName(), "Sortie");
	}

    /**
     * Permet de définir une {@link fr.beber.bean.Produit} à partir du {@link android.database.Cursor}.
     *
     * @param cursor à convertir.
     * @return Un produit.
     */
	@Override
	public Produit convertCursorToObject(final Cursor cursor) {

		final Produit exec = new Produit();

		exec.setId(cursor.getInt(BDD.PRODUIT_NUM_ID));
		exec.setName(cursor.getString(BDD.PRODUIT_NUM_NAME));
		
		return exec;
	}
	
}
