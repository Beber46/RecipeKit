package fr.beber.object;

/**
 * Classe permettant de créer un produit pour les recettes.
 *
 * @author Beber46
 * @version 1.0
 */
public class Produit {

    /**
     * Identifiant du produit
     */
	private Integer id;

    /**
     * Nom du produit
     */
	private String name;

    /**
     * Permet d'obtenir l'identifiant du produit.
     * @return Un identifiant de produit.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Permet de changer l'identifiant du produit.
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Permet d'obtenir le nom du produit.
     * @return Le nom du produit.
     */
    public String getName() {
        return name;
    }

    /**
     * Permet de changer le nom du produit.
     */
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Produit produit = (Produit) o;

        if (id != null ? !id.equals(produit.id) : produit.id != null) return false;
        if (name != null ? !name.equals(produit.name) : produit.name != null) return false;

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
        return "Produit[" +
                "id=" + id +
                ", name='" + name + '\'' +
                ']';
    }
}
