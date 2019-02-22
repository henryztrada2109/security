package com.sitecode.security.bs.service;

import com.sitecode.datadto.conf.ErrorDTO;
import com.sitecode.datadto.security.EmpresaDTO;
import com.sitecode.datadto.security.EmpresaPorUsuarioDTO;
import com.sitecode.security.bo.Empresa;

/**
 * @author Henry Hernandez
 * @version 1.0.0
 * @since 1.0.0
 * created on 1/06/18
 */

public interface EmpresaService {

    Iterable<EmpresaDTO> listAll();

    EmpresaDTO getRegistro(Integer codigo);

    EmpresaDTO guardarRegistro(Empresa entidad);

    EmpresaDTO editarRegistro(Empresa entidad);

    EmpresaDTO eliminarRegistro(Integer code);

    Iterable<EmpresaDTO> listForUsuario(Integer idUsuario);

    EmpresaDTO validaEmpresa(Integer idUsuario);

    Iterable<EmpresaPorUsuarioDTO> listAllPorUsuario(Integer idUsuario);

    ErrorDTO creaRegistroRelacion(Integer idUsuario, Integer idEmpresa);

    ErrorDTO eliminaRelacion(Integer idUsuario, Integer idEmpresa);

}
