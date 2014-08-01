package com.beber.bdd;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.beber.object.Composition;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe abstract permettant de définir les principales méthodes à utiliser dans les classes repos.
 *
 * @author Beber46
 * @version 1.0
 */
public abstract class Repository<T> implements IRepository<T>{
    /**
     * Utiliser pour ouvrir ou fermer une connexion BDD
     */
	protected SQLiteDatabase mBDD;
    /**
     * Utiliser pour ouvrir ou fermer une connexion BDD
     */
	protected SQLiteOpenHelper mSQLOH;

    /**
     * Constructeur
     */
	public Repository() {
		
	}

    /**
     * Ouverture d'une connexion BDD
     */
	public void Open(){
        mBDD = mSQLOH.getWritableDatabase();
	}

    /**
     * Fermeture d'une connexion BDD
     */
	public void Close(){
        mBDD.close();
	}

    /**
     * Permet de convertire un {@link android.database.Cursor} en liste de {@link T}.
     *
     * @param cursor à convertir.
     * @return Une liste de {@link T} trouvé.
     */
    //TODO: vérifier que cela marche
    @Override
    public List<T> ConvertCursorToListObject(final Cursor cursor) {
        final List<T> liste = new ArrayList<T>();

        if (cursor.getCount() == 0)
            return liste;

        cursor.moveToFirst();
        do {
            T exec = this.ConvertCursorToObject(cursor);
            liste.add(exec);
        } while (cursor.moveToNext());

        cursor.close();

        return liste;
    }

    /**
     * Permet de convertire un {@link android.database.Cursor} en {@link T}.
     *
     * @param cursor à convertir.
     * @return {@link T} trouvé.
     */
    //TODO: vérifier que cela marche
    @Override
    public T ConvertCursorToOneObject(final Cursor cursor) {
        c.moveToFirst();

        final T exec = ConvertCursorToObject(c);

        c.close();
        return exec;
    }
}
