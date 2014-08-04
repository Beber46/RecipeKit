package fr.beber.bean;

public class Unit {
    /**
     * Identifiant d'une unité.
     */
	private Integer id;

    /**
     * Nom d'une unité.
     */
	private String name;

    /**
     * Abréviation du nom.
     */
    private String abreviation;

    /**
     * Permet d'obtenir l'identifiant d'une unité.
     * @return Un identifiant d'une unité.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Permet de changer l'identifiant d'une unité.
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Permet d'obtenir le nom d'une unité.
     * @return Le nom d'une unité.
     */
    public String getName() {
        return name;
    }

    /**
     * Permet de changer le nom d'une unité.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Permet d'obtenir l'abreviation d'une unité.
     * @return L'abreviation d'une unité.
     */
    public String getAbreviation() {
        return abreviation;
    }

    /**
     * Permet de changer l'abreviation d'une unité.
     */
    public void setAbreviation(String abreviation) {
        this.abreviation = abreviation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Unit)) return false;

        Unit unit = (Unit) o;

        if (abreviation != null ? !abreviation.equals(unit.abreviation) : unit.abreviation != null) return false;
        if (id != null ? !id.equals(unit.id) : unit.id != null) return false;
        if (name != null ? !name.equals(unit.name) : unit.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (abreviation != null ? abreviation.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Unit[" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", abreviation='" + abreviation + '\'' +
                ']';
    }
}
