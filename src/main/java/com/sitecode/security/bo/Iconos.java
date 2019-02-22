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
public class Iconos implements Serializable{

    private static final long serialVersionUID = 7176428109269469686L;

    @Id
    @GeneratedValue
    private Integer idIcono;
    private String nombre;
    private String clase;

    public Iconos() {
    }

    public Iconos(String nombre, String clase) {
        this.nombre = nombre;
        this.clase = clase;
    }

    public Integer getIdIcono() {
        return idIcono;
    }

    public void setIdIcono(Integer idIcono) {
        this.idIcono = idIcono;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getClase() {
        return clase;
    }

    public void setClase(String clase) {
        this.clase = clase;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Iconos iconos = (Iconos) o;

        if (!idIcono.equals(iconos.idIcono)) return false;
        if (!nombre.equals(iconos.nombre)) return false;
        return clase.equals(iconos.clase);
    }

    @Override
    public int hashCode() {
        int result = idIcono.hashCode();
        result = 31 * result + nombre.hashCode();
        result = 31 * result + clase.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Iconos{" +
                "idIcono=" + idIcono +
                ", nombre='" + nombre + '\'' +
                ", clase='" + clase + '\'' +
                '}';
    }
}
