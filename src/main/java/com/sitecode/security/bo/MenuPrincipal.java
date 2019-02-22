package com.sitecode.security.bo;


import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Henry Hernandez
 * @version 1.0.0
 * @since 1.0.0
 * created on 22/06/18
 */

@Entity
public class MenuPrincipal implements Serializable{

    private static final long serialVersionUID = 7176428109269469686L;

    @Id
    @GeneratedValue
    private Integer idMenuPrincipal;
    private Integer orden;
    private String descripcion;
    private Integer idOpciones;
    private Integer idIcono;
    private Integer idTipoMenu;
    private Integer ejecuta;

    public MenuPrincipal() {
    }

    public MenuPrincipal(Integer orden, String descripcion, Integer idOpciones, Integer idIcono, Integer idTipoMenu,
                         Integer ejecuta) {
        this.orden = orden;
        this.descripcion = descripcion;
        this.idOpciones = idOpciones;
        this.idIcono = idIcono;
        this.idTipoMenu = idTipoMenu;
        this.ejecuta = ejecuta;
    }

    public Integer getIdMenuPrincipal() {
        return idMenuPrincipal;
    }

    public void setIdMenuPrincipal(Integer idMenuPrincipal) {
        this.idMenuPrincipal = idMenuPrincipal;
    }

    public Integer getOrden() {
        return orden;
    }

    public void setOrden(Integer orden) {
        this.orden = orden;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getIdOpciones() {
        return idOpciones;
    }

    public void setIdOpciones(Integer idOpciones) {
        this.idOpciones = idOpciones;
    }

    public Integer getIdIcono() {
        return idIcono;
    }

    public void setIdIcono(Integer idIcono) {
        this.idIcono = idIcono;
    }

    public Integer getIdTipoMenu() {
        return idTipoMenu;
    }

    public void setIdTipoMenu(Integer idTipoMenu) {
        this.idTipoMenu = idTipoMenu;
    }

    public Integer getEjecuta() {
        return ejecuta;
    }

    public void setEjecuta(Integer ejecuta) {
        this.ejecuta = ejecuta;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MenuPrincipal that = (MenuPrincipal) o;

        if (!idMenuPrincipal.equals(that.idMenuPrincipal)) return false;
        if (!orden.equals(that.orden)) return false;
        if (!descripcion.equals(that.descripcion)) return false;
        if (!idOpciones.equals(that.idOpciones)) return false;
        if (!idIcono.equals(that.idIcono)) return false;
        if (!idTipoMenu.equals(that.idTipoMenu)) return false;
        return ejecuta.equals(that.ejecuta);
    }

    @Override
    public int hashCode() {
        int result = idMenuPrincipal.hashCode();
        result = 31 * result + orden.hashCode();
        result = 31 * result + descripcion.hashCode();
        result = 31 * result + idOpciones.hashCode();
        result = 31 * result + idIcono.hashCode();
        result = 31 * result + idTipoMenu.hashCode();
        result = 31 * result + ejecuta.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "MenuPrincipal{" +
                "idMenuPrincipal=" + idMenuPrincipal +
                ", orden=" + orden +
                ", descripcion='" + descripcion + '\'' +
                ", idOpciones=" + idOpciones +
                ", idIcono=" + idIcono +
                ", idTipoMenu=" + idTipoMenu +
                ", ejecuta=" + ejecuta +
                '}';
    }
}
