package fr.beber.object;

/**
 * Classe de recette qui permet d'identifier une recette.
 *
 * @author Beber46
 * @version 1.0
 */
public class Recette {

    /**
     * Identifiant de la recette
     */
	private Integer id;

    /**
     * Nom de la recette
     */
	private String name;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Recette recette = (Recette) o;

        if (id != null ? !id.equals(recette.id) : recette.id != null) return false;
        if (name != null ? !name.equals(recette.name) : recette.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Recette[" +
                "id=" + id +
                ", name='" + name + '\'' +
                ']';
    }
}
