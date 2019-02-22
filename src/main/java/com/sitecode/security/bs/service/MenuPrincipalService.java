package com.sitecode.security.bs.service;

import com.sitecode.datadto.security.EstructuraMenuDTO;
import com.sitecode.datadto.security.MenuPrincipalDTO;
import com.sitecode.security.bo.MenuPrincipal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author Henry Hernandez
 * @version 1.0.0
 * @since 1.0.0
 * created on 1/06/18
 */

public interface MenuPrincipalService {

    Page<MenuPrincipalDTO> listAll(Pageable page, Integer tipoMenu);

    Iterable<MenuPrincipalDTO> listAll(Integer tipoMenu);

    MenuPrincipalDTO getRegistro(Integer codigo);

    MenuPrincipalDTO guardarRegistro(MenuPrincipal entidad);

    MenuPrincipalDTO editarRegistro(MenuPrincipal entidad);

    MenuPrincipalDTO eliminarRegistro(Integer code);

    Iterable<EstructuraMenuDTO> listEstructura(Integer tipoMenu);

}
