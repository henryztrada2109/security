package com.sitecode.security.bo;


import javax.persistence.*;

/**
 * @author Henry Hernandez
 * @version 1.0.0
 * @since 1.0.0
 * created on 1/06/18
 */

@Entity
public class Empresa {

    private static final long serialVersionUID = 7176428109269469686L;

    @Id
    @GeneratedValue
    private Integer idEmpresa;
    private Integer codigo;
    private String nombre;

    public Empresa() {
    }

    public Empresa(Integer idEmpresa, Integer codigo, String nombre) {
        this.idEmpresa = idEmpresa;
        this.codigo = codigo;
        this.nombre = nombre;
    }

    public Integer getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(Integer idEmpresa) {
        this.idEmpresa = idEmpresa;
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

        Empresa empresa = (Empresa) o;

        if (!idEmpresa.equals(empresa.idEmpresa)) return false;
        if (!codigo.equals(empresa.codigo)) return false;
        return nombre.equals(empresa.nombre);
    }

    @Override
    public int hashCode() {
        int result = idEmpresa.hashCode();
        result = 31 * result + codigo.hashCode();
        result = 31 * result + nombre.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Empresa{" +
                "idEmpresa=" + idEmpresa +
                ", codigo=" + codigo +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}
