package com.sitecode.security.bs.service;

import com.sitecode.datadto.security.UsuarioDTO;
import com.sitecode.security.bo.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author Henry Hernandez
 * @version 1.0.0
 * @since 1.0.0
 * created on 1/06/18
 */

public interface UsuarioService {

    Page<UsuarioDTO> listAll(Pageable page);

    Page<UsuarioDTO> listAll(Pageable page, String usuario);

    Page<UsuarioDTO> listAll(Pageable page, Integer codigo);

    Page<UsuarioDTO> listAll(Pageable page, String usuario, Integer codigo);

    UsuarioDTO getRegistro(Integer codigo);

    UsuarioDTO guardarRegistro(Usuario entidad, Integer idEmpresa);

    UsuarioDTO editarRegistro(Usuario entidad);

    UsuarioDTO eliminarRegistro(Integer code);

    UsuarioDTO validarUsuario(String usuario, String pass);

}
