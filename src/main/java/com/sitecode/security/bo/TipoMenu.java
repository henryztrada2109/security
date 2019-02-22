package com.sitecode.security.bo;


import javax.persistence.*;

/**
 * @author Henry Hernandez
 * @version 1.0.0
 * @since 1.0.0
 * created on 1/06/18
 */

@Entity
public class TipoMenu {

    private static final long serialVersionUID = 7176428109269469686L;

    @Id
    @GeneratedValue
    private Integer idTipoMenu;
    private Integer codigo;
    private String descripcion;

    public TipoMenu() {
    }

    public TipoMenu(Integer codigo, String descripcion) {
        this.codigo = codigo;
        this.descripcion = descripcion;
    }

    public Integer getIdTipoMenu() {
        return idTipoMenu;
    }

    public void setIdTipoMenu(Integer idTipoMenu) {
        this.idTipoMenu = idTipoMenu;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TipoMenu tipoMenu = (TipoMenu) o;

        if (!idTipoMenu.equals(tipoMenu.idTipoMenu)) return false;
        if (!codigo.equals(tipoMenu.codigo)) return false;
        return descripcion.equals(tipoMenu.descripcion);
    }

    @Override
    public int hashCode() {
        int result = idTipoMenu.hashCode();
        result = 31 * result + codigo.hashCode();
        result = 31 * result + descripcion.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "TipoMenu{" +
                "idTipoMenu=" + idTipoMenu +
                ", codigo=" + codigo +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}
