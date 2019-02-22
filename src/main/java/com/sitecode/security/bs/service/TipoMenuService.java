package com.sitecode.security.bs.service;

import com.sitecode.datadto.security.TipoMenuDTO;
import com.sitecode.security.bo.TipoMenu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author Henry Hernandez
 * @version 1.0.0
 * @since 1.0.0
 * created on 1/06/18
 */

public interface TipoMenuService {

    Page<TipoMenuDTO> listAll(Pageable page);

    Iterable<TipoMenuDTO> listAll();

    TipoMenuDTO getRegistro(Integer codigo);

    TipoMenuDTO guardarRegistro(TipoMenu entidad);

    TipoMenuDTO editarRegistro(TipoMenu entidad);

    TipoMenuDTO eliminarRegistro(Integer code);

}
