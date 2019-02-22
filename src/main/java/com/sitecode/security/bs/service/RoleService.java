package com.sitecode.security.bs.service;

import com.sitecode.datadto.security.RoleDTO;
import com.sitecode.security.bo.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author Henry Hernandez
 * @version 1.0.0
 * @since 1.0.0
 * created on 1/06/18
 */

public interface RoleService {

    Page<RoleDTO> listAll(Pageable pageable);

    Iterable<RoleDTO> listAll();

    RoleDTO getRegistro(Integer codigo);

    RoleDTO guardarRegistro(Role entidad);

    RoleDTO editarRegistro(Role entidad);

    RoleDTO eliminarRegistro(Integer code);

}
