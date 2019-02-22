package com.sitecode.security.bs.service;

import com.sitecode.datadto.security.IconosDTO;

/**
 * @author Henry Hernandez
 * @version 1.0.0
 * @since 1.0.0
 * created on 27/06/18
 */

public interface IconosService {

    Iterable<IconosDTO> listAll();

    IconosDTO getIcono(Integer id);

}
