package fr.beber.bdd.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
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
     *
     * @param context Le contexte courant.
     */
    public CompositionDAO(final Context context) {
        mSQLOH = new BDD(context, null);
    }

    /**
     * Constructeur
     *
     * @param sqLiteOpenHelper Définit le contexte à utiliser.
     */
    public CompositionDAO(final SQLiteOpenHelper sqLiteOpenHelper) {
        mSQLOH = sqLiteOpenHelper;
    }

    /**
     * Permet de récupérer la liste des Compositions.
     *
     * @return La liste des compositions trouvée.
     */
    @Override
    public List<Composition> getAll() {
        Log.d(this.getClass().getName(), "Entree");
        final Cursor c = mBDD.query(BDD.TN_COMPOSITION, mColumn, null, null, null, null, null);

        Log.d(this.getClass().getName(), "Sortie");
        return this.convertCursorToListObject(c);
    }

    /**
     * Permet de récupérer une compositino en fonction de son identifiant <code>id</code>.
     *
     * @param id Identifiant d'une composition.
     * @return La composition trouvé.
     */
    @Override
    public Composition getById(final Integer id) {
        Log.d(this.getClass().getName(), "Entree");
        final Cursor c = mBDD.query(BDD.TN_COMPOSITION, mColumn, String.valueOf(id), null, null, null, null);

        Log.d(this.getClass().getName(), "Sortie");
        return convertCursorToOneObject(c);
    }

    /**
     * Permet de retourner la liste des compositions d'une recette en fonction de l'identifiant d'une recette.
     *
     * @param idRecette Identifiant d'une recette.
     * @return Une liste de composition.
     */
    public List<Composition> getByIdRecette(final Integer idRecette) {
        Log.d(this.getClass().getName(), "Entree");
        final Cursor cursor = mBDD.query(BDD.TN_COMPOSITION, mColumn, BDD.COMPOSITION_COLUMN_ID_RECETTE + " = " + String.valueOf(idRecette), null, null, null, null);

        Log.d(this.getClass().getName(), "Sortie");
        return this.convertCursorToListObject(cursor);
    }

    /**
     * Permet d'enregistrer une composition.
     *
     * @param composition à enregistrer.
     */
    @Override
    public void save(final Composition composition) {
        Log.d(this.getClass().getName(), "Entree");

        final ContentValues contentValues = this.getContentValues(composition);

        mBDD.insert(BDD.TN_COMPOSITION, null, contentValues);
        Log.d(this.getClass().getName(), "Sortie");
    }

    /**
     * Permet de mettre à jour une composition.
     *
     * @param composition à mettre à jour.
     */
    @Override
    public void update(final Composition composition) {
        Log.d(this.getClass().getName(), "Entree");
        final ContentValues contentValues = this.getContentValues(composition);

        mBDD.update(BDD.TN_COMPOSITION, contentValues, mColumn[0] + "=?", new String[]{String.valueOf(composition.getId())});
        Log.d(this.getClass().getName(), "Sortie");
    }

    /**
     * Permet de créer un ContentValues à partir d'une composition.
     *
     * @param composition à convertir.
     * @return Le contentValues obtenu.
     */
    private ContentValues getContentValues(final Composition composition) {
        final ContentValues contentValues = new ContentValues();

        contentValues.put(mColumn[1], composition.getProduit().getId());
        contentValues.put(mColumn[2], composition.getIdRecette());
        contentValues.put(mColumn[3], composition.getQuantite());
        contentValues.put(mColumn[4], composition.getUnit() != null ? composition.getUnit().getId() : null);

        return contentValues;
    }

    /**
     * Permet de supprimer une composition en fonction de son identifiant.
     *
     * @param id Identifiant d'une composition.
     */
    @Override
    public void delete(final Integer id) {
        Log.d(this.getClass().getName(), "Entree");
        mBDD.delete(BDD.TN_COMPOSITION, mColumn[0] + "=?", new String[]{String.valueOf(id)});
        Log.d(this.getClass().getName(), "Sortie");
    }

    /**
     * Permet de définir une {@link fr.beber.bean.Composition} à partir du {@link android.database.Cursor}.
     *
     * @param cursor à convertir.
     * @return Une compositiion.
     */
    @Override
    public Composition convertCursorToObject(Cursor cursor) {

        final Composition composition = new Composition();

        final ProduitDAO produitDAO = new ProduitDAO(mSQLOH);
        final UnitDAO unitDAO = new UnitDAO(mSQLOH);
        composition.setId(cursor.getInt(BDD.COMPOSITION_NUM_ID));
        composition.setProduit(produitDAO.getById(cursor.getInt(BDD.COMPOSITION_NUM_ID_PRODUIT)));
        composition.setIdRecette(cursor.getInt(BDD.COMPOSITION_NUM_ID_RECETTE));
        composition.setQuantite(cursor.getFloat(BDD.COMPOSITION_NUM_QUANTITE));
        composition.setUnit(cursor.getInt(BDD.COMPOSITION_NUM_ID_UNIT) > 0 ? unitDAO.getById(cursor.getInt(BDD.COMPOSITION_NUM_ID_UNIT)) : null);

        return composition;
    }

}
