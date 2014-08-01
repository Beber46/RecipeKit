package com.beber.bdd;

import java.util.List;

import android.database.Cursor;

/**
 * Interface qui permet de cr�er les m�thodes par d�faut utilis�es par tous les repo en base.
 *
 * @author Beber46
 * @version 1.0
 */
public interface IRepository<T> {

    /**
     * Permet de r�cup�rer la liste des {@link T}.
     *
     * @return La liste de {@link T} trouv�e.
     */
	public List<T> GetAll();

    /**
     * Permet de r�cup�rer un {@link T} en fonction de son identifiant <code>id</code>.
     *
     * @param id Identifiant d'un {@link T}.
     * @return {@link T} trouv�.
     */
    public T GetById(final Integer id);

    /**
     * Permet d'enregistrer un {@link T}.
     *
     * @param entite � enregistrer.
     */
    public void Save(final T entite);

    /**
     * Permet de mettre � jour un {@link T}.
     *
     * @param entite � mettre � jour.
     */
    public void Update(final T entite);

    /**
     * Permet de supprimer un {@link T} en fonction de son identifiant.
     *
     * @param id Identifiant d'un {@link T}.
     */
    public void Delete(final Integer id);

    /**
     * Permet de convertire un {@link android.database.Cursor} en liste de {@link T}.
     *
     * @param cursor � convertir.
     * @return Une liste de {@link T} trouv�.
     */
    public List<T> ConvertCursorToListObject(Cursor c);

    /**
     * M�thode utilis�e par {@link #ConvertCursorToObject(Cursor)} et {@link #ConvertCursorToListObject(Cursor)}.
     *
     * @param cursor � convertir.
     * @return Une compositiion.
     */
    public T ConvertCursorToObject(Cursor c);

    /**
     * Permet de convertire un {@link android.database.Cursor} en {@link T}.
     *
     * @param cursor � convertir.
     * @return {@link T} trouv�.
     */
    public T ConvertCursorToOneObject(Cursor c);
}
