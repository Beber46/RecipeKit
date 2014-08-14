package fr.beber.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe de recette qui permet d'identifier une recette.
 *
 * @author Beber46
 * @version 1.0
 */
public class Recette {

    /**
     * Identifiant de la recette.
     */
    private Integer id;

    /**
     * Nom de la recette.
     */
    private String name;

    /**
     * Descriptif de la préparation.
     */
    private String preparation;

    /**
     * Temps de la préparation.
     */
    private Integer tempsPreparation;

    /**
     * Temps de la cuisson.
     */
    private Integer tempsCuisson;

    /**
     * Permet d'avoir une note pour la recette attribué par l'utilisateur.
     */
    private Float note;

    /**
     * Nombre de personne pour la recette.
     */
    private Integer nbPersonne;

    /**
     * Liste permettant de connaitre la composition d'une recette
     */
    private List<Composition> compositionList;

    public Recette() {
        this.compositionList = new ArrayList<Composition>();
    }

    /**
     * Permet d'obtenir l'identifiant de la recette.
     *
     * @return Un identifiant de recette.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Permet de changer l'identifiant de la recette.
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Permet d'obtenir le nom de la recette.
     *
     * @return Le nom de recette.
     */
    public String getName() {
        return name;
    }

    /**
     * Permet de changer le nom de la recette.
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * Permet d'obtenir la préparation de la recette.
     *
     * @return La préparation de recette.
     */
    public String getPreparation() {
        return preparation;
    }

    /**
     * Permet de changer la préparation de la recette.
     */
    public void setPreparation(final String preparation) {
        this.preparation = preparation;
    }

    /**
     * Permet d'obtenir le temps de préparation de la recette.
     *
     * @return Le temps de cuisson de recette.
     */
    public Integer getTempsPreparation() {
        return tempsPreparation;
    }

    /**
     * Permet de changer le temps de préparation de la recette.
     */
    public void setTempsPreparation(final Integer tempsPreparation) {
        this.tempsPreparation = tempsPreparation;
    }

    /**
     * Permet d'obtenir le temps de cuisson de la recette.
     *
     * @return Le temps de cuisson de recette.
     */
    public Integer getTempsCuisson() {
        return tempsCuisson;
    }

    /**
     * Permet de changer le temps de cuisson de la recette.
     */
    public void setTempsCuisson(final Integer tempsCuisson) {
        this.tempsCuisson = tempsCuisson;
    }

    /**
     * Permet d'obtenir la note de la recette.
     *
     * @return Un entier compris entre 1-5.
     */
    public Float getNote() {
        return note;
    }

    /**
     * Change la note de la recette.
     *
     * @param note de la recette à changer.
     */
    public void setNote(final Float note) {
        this.note = note;
    }

    /**
     * Permet d'obtenir le nombre de personne de la recette.
     *
     * @return Le nombre de personne.
     */
    public Integer getNbPersonne() {
        return nbPersonne;
    }

    /**
     * Change le nombre de personne de la recette.
     *
     * @param nbPersonne de la recette à changer.
     */
    public void setNbPersonne(Integer nbPersonne) {
        this.nbPersonne = nbPersonne;
    }

    /**
     * Permet d'obtenir la liste des compositions.
     *
     * @return Liste des compositions.
     */
    public List<Composition> getCompositionList() {
        return compositionList;
    }

    /**
     * Permet de changer la liste des compositions.
     */
    public void setCompositionList(final List<Composition> compositionList) {
        this.compositionList = compositionList;
    }

    /**
     * Permet d'ajouter une composition à la liste de composition de la recette.
     *
     * @param composition à ajouter à la liste de composition.
     */
    public void addCompositionList(Composition composition) {
        this.compositionList.add(composition);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Recette recette = (Recette) o;

        if (compositionList != null ? !compositionList.equals(recette.compositionList) : recette.compositionList != null)
            return false;
        if (id != null ? !id.equals(recette.id) : recette.id != null) return false;
        if (name != null ? !name.equals(recette.name) : recette.name != null) return false;
        if (note != null ? !note.equals(recette.note) : recette.note != null) return false;
        if (nbPersonne != null ? !nbPersonne.equals(recette.nbPersonne) : recette.nbPersonne != null) return false;
        if (preparation != null ? !preparation.equals(recette.preparation) : recette.preparation != null) return false;
        if (tempsCuisson != null ? !tempsCuisson.equals(recette.tempsCuisson) : recette.tempsCuisson != null)
            return false;
        if (tempsPreparation != null ? !tempsPreparation.equals(recette.tempsPreparation) : recette.tempsPreparation != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (preparation != null ? preparation.hashCode() : 0);
        result = 31 * result + (tempsPreparation != null ? tempsPreparation.hashCode() : 0);
        result = 31 * result + (tempsCuisson != null ? tempsCuisson.hashCode() : 0);
        result = 31 * result + (note != null ? note.hashCode() : 0);
        result = 31 * result + (nbPersonne != null ? nbPersonne.hashCode() : 0);
        result = 31 * result + (compositionList != null ? compositionList.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Recette[" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", preparation='" + preparation + '\'' +
                ", tempsPreparation=" + tempsPreparation +
                ", tempsCuisson=" + tempsCuisson +
                ", note=" + note +
                ", nbPersonne=" + nbPersonne +
                ", compositionList=" + compositionList +
                ']';
    }
}
