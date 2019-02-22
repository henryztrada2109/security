package com.sitecode.security.bo;


import javax.persistence.*;

/**
 * @author Henry Hernandez
 * @version 1.0.0
 * @since 1.0.0
 * created on 4/07/18
 */

@Entity
public class RelacionEmpresaUsuario {

    private static final long serialVersionUID = 7176428109269469686L;

    @Id
    @GeneratedValue
    private Integer idRelacion;
    private Integer idEmpresa;
    private Integer idUsuario;

    public RelacionEmpresaUsuario() {
    }

    public RelacionEmpresaUsuario(Integer idEmpresa, Integer idUsuario) {
        this.idEmpresa = idEmpresa;
        this.idUsuario = idUsuario;
    }

    public Integer getIdRelacion() {
        return idRelacion;
    }

    public void setIdRelacion(Integer idRelacion) {
        this.idRelacion = idRelacion;
    }

    public Integer getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(Integer idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RelacionEmpresaUsuario that = (RelacionEmpresaUsuario) o;

        if (!idRelacion.equals(that.idRelacion)) return false;
        if (!idEmpresa.equals(that.idEmpresa)) return false;
        return idUsuario.equals(that.idUsuario);
    }

    @Override
    public int hashCode() {
        int result = idRelacion.hashCode();
        result = 31 * result + idEmpresa.hashCode();
        result = 31 * result + idUsuario.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "RelacionEmpresaUsuario{" +
                "idRelacion=" + idRelacion +
                ", idEmpresa=" + idEmpresa +
                ", idUsuario=" + idUsuario +
                '}';
    }
}
