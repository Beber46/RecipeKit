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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Unit unit = (Unit) o;

        if (id != null ? !id.equals(unit.id) : unit.id != null) return false;
        if (name != null ? !name.equals(unit.name) : unit.name != null) return false;

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
        return "Unit[" +
                "id=" + id +
                ", name='" + name + '\'' +
                ']';
    }
}
