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
public class Opciones implements Serializable{

    private static final long serialVersionUID = 7176428109269469686L;

    @Id
    @GeneratedValue
    private Integer idOpciones;
    private String descripcion;
    private String path;

    public Opciones() {
    }

    public Opciones(String descripcion, String path) {
        this.descripcion = descripcion;
        this.path = path;
    }

    public Integer getIdOpciones() {
        return idOpciones;
    }

    public void setIdOpciones(Integer idOpciones) {
        this.idOpciones = idOpciones;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Opciones opciones = (Opciones) o;

        if (!idOpciones.equals(opciones.idOpciones)) return false;
        if (!descripcion.equals(opciones.descripcion)) return false;
        return path.equals(opciones.path);
    }

    @Override
    public int hashCode() {
        int result = idOpciones.hashCode();
        result = 31 * result + descripcion.hashCode();
        result = 31 * result + path.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Opciones{" +
                "idOpciones=" + idOpciones +
                ", descripcion='" + descripcion + '\'' +
                ", path='" + path + '\'' +
                '}';
    }
}
