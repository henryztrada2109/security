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
public class Usuario implements Serializable{

    private static final long serialVersionUID = 7176428109269469686L;

    @Id
    @GeneratedValue
    private Integer idUsuario;
    private String usuarioApp;
    private String pass;
    private Integer idRole;
    private Integer estado;
    private Integer idTipoMenu;

    public Usuario() {
    }

    public Usuario(String usuarioApp, String pass, Integer idRole, Integer estado, Integer idTipoMenu) {
        this.usuarioApp = usuarioApp;
        this.pass = pass;
        this.idRole = idRole;
        this.estado = estado;
        this.idTipoMenu = idTipoMenu;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getUsuarioApp() {
        return usuarioApp;
    }

    public void setUsuarioApp(String usuarioApp) {
        this.usuarioApp = usuarioApp;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public Integer getIdRole() {
        return idRole;
    }

    public void setIdRole(Integer idRole) {
        this.idRole = idRole;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public Integer getIdTipoMenu() {
        return idTipoMenu;
    }

    public void setIdTipoMenu(Integer idTipoMenu) {
        this.idTipoMenu = idTipoMenu;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Usuario usuario = (Usuario) o;

        if (!idUsuario.equals(usuario.idUsuario)) return false;
        if (!usuarioApp.equals(usuario.usuarioApp)) return false;
        if (!pass.equals(usuario.pass)) return false;
        if (!idRole.equals(usuario.idRole)) return false;
        if (!estado.equals(usuario.estado)) return false;
        return idTipoMenu.equals(usuario.idTipoMenu);
    }

    @Override
    public int hashCode() {
        int result = idUsuario.hashCode();
        result = 31 * result + usuarioApp.hashCode();
        result = 31 * result + pass.hashCode();
        result = 31 * result + idRole.hashCode();
        result = 31 * result + estado.hashCode();
        result = 31 * result + idTipoMenu.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "idUsuario=" + idUsuario +
                ", usuarioApp='" + usuarioApp + '\'' +
                ", pass='" + pass + '\'' +
                ", idRole=" + idRole +
                ", estado=" + estado +
                ", idTipoMenu=" + idTipoMenu +
                '}';
    }

}
