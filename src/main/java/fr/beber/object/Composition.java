package fr.beber.object;

/**
 * Classe de composition qui permet d'associé une recette avec la quantité d'un produit.
 *
 * @author Beber46
 * @version 1.0
 */
public class Composition {
    /**
     * Identifiant de la composition.
     */
	private Integer id;

    /**
     * Identifiant d'un produit.
     */
	private Integer idProduit;

    /**
     * Identifiant de la recette.
     */
	private Integer idRecette;

    /**
     * Quantite de produit dans la recette.
     */
	private Integer quantite;

    /**
     * Idnetifiant de l'unité de la quantite.
     */
	private Integer idUnit;

    /**
     * Permet d'obtenir l'identifiant de la composition.
     * @return Un identifiant de composition.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Permet de changer l'identifiant de la composition.
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Permet d'obtenir l'identifiant du produit.
     * @return Un identifiant de produit.
     */
    public Integer getIdProduit() {
        return idProduit;
    }

    /**
     * Permet de changer l'identifiant du produit.
     */
    public void setIdProduit(Integer idProduit) {
        this.idProduit = idProduit;
    }

    /**
     * Permet d'obtenir l'identifiant de la recette.
     * @return Un identifiant de recette.
     */
    public Integer getIdRecette() {
        return idRecette;
    }

    /**
     * Permet de changer l'identifiant de la recette.
     */
    public void setIdRecette(Integer idRecette) {
        this.idRecette = idRecette;
    }

    /**
     * Permet d'obtenir la quantité de produit de la composition.
     * @return Quantité de produit.
     */
    public Integer getQuantite() {
        return quantite;
    }

    /**
     * Permet de changer la quantité de produit de la composition.
     */
    public void setQuantite(Integer quantite) {
        this.quantite = quantite;
    }

    /**
     * Permet d'obtenir l'identifiant de l'unité.
     * @return Un identifiant de l'unité.
     */
    public Integer getIdUnit() {
        return idUnit;
    }

    /**
     * Permet de changer l'identifiant de l'unité.
     */
    public void setIdUnit(Integer idUnit) {
        this.idUnit = idUnit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Composition)) return false;

        Composition that = (Composition) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (idProduit != null ? !idProduit.equals(that.idProduit) : that.idProduit != null) return false;
        if (idRecette != null ? !idRecette.equals(that.idRecette) : that.idRecette != null) return false;
        if (idUnit != null ? !idUnit.equals(that.idUnit) : that.idUnit != null) return false;
        if (quantite != null ? !quantite.equals(that.quantite) : that.quantite != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (idProduit != null ? idProduit.hashCode() : 0);
        result = 31 * result + (idRecette != null ? idRecette.hashCode() : 0);
        result = 31 * result + (quantite != null ? quantite.hashCode() : 0);
        result = 31 * result + (idUnit != null ? idUnit.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Composition[" +
                "id=" + id +
                ", idProduit=" + idProduit +
                ", idRecette=" + idRecette +
                ", quantite=" + quantite +
                ", idUnit=" + idUnit +
                ']';
    }
}
