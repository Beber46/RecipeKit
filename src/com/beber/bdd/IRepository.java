package com.beber.bdd;

import java.util.List;

import android.database.Cursor;

/**
 * Interface qui permet de créer les méthodes par défaut utilisées par tous les repo en base.
 *
 * @author Beber46
 * @version 1.0
 */
public interface IRepository<T> {

    /**
     * Permet de récupérer la liste des {@link T}.
     *
     * @return La liste de {@link T} trouvée.
     */
	public List<T> GetAll();

    /**
     * Permet de récupérer un {@link T} en fonction de son identifiant <code>id</code>.
     *
     * @param id Identifiant d'un {@link T}.
     * @return {@link T} trouvé.
     */
    public T GetById(final Integer id);

    /**
     * Permet d'enregistrer un {@link T}.
     *
     * @param entite à enregistrer.
     */
    public void Save(final T entite);

    /**
     * Permet de mettre à jour un {@link T}.
     *
     * @param entite à mettre à jour.
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
     * @param cursor à convertir.
     * @return Une liste de {@link T} trouvé.
     */
    public List<T> ConvertCursorToListObject(Cursor c);

    /**
     * Méthode utilisée par {@link #ConvertCursorToObject(Cursor)} et {@link #ConvertCursorToListObject(Cursor)}.
     *
     * @param cursor à convertir.
     * @return Une compositiion.
     */
    public T ConvertCursorToObject(Cursor c);

    /**
     * Permet de convertire un {@link android.database.Cursor} en {@link T}.
     *
     * @param cursor à convertir.
     * @return {@link T} trouvé.
     */
    public T ConvertCursorToOneObject(Cursor c);
}
