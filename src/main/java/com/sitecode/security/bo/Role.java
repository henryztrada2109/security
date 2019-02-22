package com.sitecode.security.bo;

import javax.persistence.*;
import java.io.Serializable;


/**
 * @author Henry Hernandez
 * @version 1.0.0
 * @since 1.0.0
 * created on 1/06/18
 */

@Entity
public class Role implements Serializable{

    private static final long serialVersionUID = 7176428109269469686L;

    @Id
    @GeneratedValue
    private Integer idRole;
    private Integer codigo;
    private String nombre;

    public Role() {
    }

    public Role(Integer codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
    }

    public Integer getIdRole() {
        return idRole;
    }

    public void setIdRole(Integer idRole) {
        this.idRole = idRole;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Role role = (Role) o;

        if (!idRole.equals(role.idRole)) return false;
        if (!codigo.equals(role.codigo)) return false;
        return nombre.equals(role.nombre);
    }

    @Override
    public int hashCode() {
        int result = idRole.hashCode();
        result = 31 * result + codigo.hashCode();
        result = 31 * result + nombre.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Role{" +
                "idRole=" + idRole +
                ", codigo='" + codigo + '\'' +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}
