package fr.beber.bean;

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
     *  Temps de la préparation.
     */
    private Integer tempsPreparation;

    /**
     * Temps de la cuisson.
     */
    private Integer tempsCuisson;

    /**
     * Permet d'obtenir l'identifiant de la recette.
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Recette)) return false;

        Recette recette = (Recette) o;

        if (id != null ? !id.equals(recette.id) : recette.id != null) return false;
        if (name != null ? !name.equals(recette.name) : recette.name != null) return false;
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
                ']';
    }
}
