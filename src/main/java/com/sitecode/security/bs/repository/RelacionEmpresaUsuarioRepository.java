package com.sitecode.security.bs.repository;

import com.sitecode.security.bo.RelacionEmpresaUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author Henry Hernandez
 * @version 1.0.0
 * @since 1.0.0
 * created on 4/07/18
 */

public interface RelacionEmpresaUsuarioRepository extends JpaRepository<RelacionEmpresaUsuario, Integer>, JpaSpecificationExecutor<Integer> {

    Iterable<RelacionEmpresaUsuario> findByIdUsuario(Integer idUsuario);

    RelacionEmpresaUsuario findByIdUsuarioAndIdEmpresa(Integer idUsuario, Integer idEmpresa);

}
