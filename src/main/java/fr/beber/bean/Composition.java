package fr.beber.bean;

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
     * Produit présent dans la composition;
     */
    private Produit produit;

    /**
     * Identifiant de la recette.
     */
    private Integer idRecette;

    /**
     * Quantite de produit dans la recette.
     */
    private Float quantite;

    /**
     * Unité présent dans la composition.
     */
    private Unit unit;

    /**
     * Permet d'obtenir l'identifiant de la composition.
     *
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
     * Permet d'obtenir le produit de la composition.
     *
     * @return Le produit de la composition.
     */
    public Produit getProduit() {
        return produit;
    }

    /**
     * Permet de changer le produit de la composition.
     */
    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    /**
     * Permet d'obtenir l'identifiant de la recette.
     *
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
     *
     * @return Quantité de produit.
     */
    public Float getQuantite() {
        return quantite;
    }

    /**
     * Permet de changer la quantité de produit de la composition.
     */
    public void setQuantite(Float quantite) {
        this.quantite = quantite;
    }

    /**
     * Permet d'obtenir l'unité.
     *
     * @return L'unité.
     */
    public Unit getUnit() {
        return unit;
    }

    /**
     * Permet de changer l'unité.
     */
    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Composition)) return false;

        Composition that = (Composition) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (idRecette != null ? !idRecette.equals(that.idRecette) : that.idRecette != null) return false;
        if (unit != null ? !unit.equals(that.unit) : that.unit != null) return false;
        if (produit != null ? !produit.equals(that.produit) : that.produit != null) return false;
        if (quantite != null ? !quantite.equals(that.quantite) : that.quantite != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (produit != null ? produit.hashCode() : 0);
        result = 31 * result + (idRecette != null ? idRecette.hashCode() : 0);
        result = 31 * result + (quantite != null ? quantite.hashCode() : 0);
        result = 31 * result + (unit != null ? unit.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Composition[" +
                "id=" + id +
                ", produit=" + produit +
                ", idRecette=" + idRecette +
                ", quantite=" + quantite +
                ", unit=" + unit +
                ']';
    }
}
