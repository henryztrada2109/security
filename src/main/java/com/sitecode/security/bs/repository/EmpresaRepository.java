package com.sitecode.security.bs.repository;

import com.sitecode.security.bo.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author Henry Hernandez
 * @version 1.0.0
 * @since 1.0.0
 * created on 1/06/18
 */

public interface EmpresaRepository extends JpaRepository<Empresa, Integer>, JpaSpecificationExecutor<Integer> {

    Empresa findByCodigo(Integer codigo);
}
