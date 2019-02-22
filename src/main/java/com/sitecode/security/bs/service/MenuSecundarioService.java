package com.sitecode.security.bs.service;

import com.sitecode.datadto.security.MenuSecundarioDTO;
import com.sitecode.security.bo.MenuSecundario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author Henry Hernandez
 * @version 1.0.0
 * @since 1.0.0
 * created on 24/06/18
 */

public interface MenuSecundarioService {

    Page<MenuSecundarioDTO> listAll(Pageable page, Integer menuPrincipal);

    Iterable<MenuSecundarioDTO> listAll(Integer menuPrincipal);

    MenuSecundarioDTO getRegistro(Integer codigo);

    MenuSecundarioDTO guardarRegistro(MenuSecundario entidad);

    MenuSecundarioDTO editarRegistro(MenuSecundario entidad);

    MenuSecundarioDTO eliminarRegistro(Integer code);

}
