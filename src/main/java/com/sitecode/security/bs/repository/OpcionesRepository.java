package com.sitecode.security.bs.repository;

import com.sitecode.security.bo.Opciones;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author Henry Hernandez
 * @version 1.0.0
 * @since 1.0.0
 * created on 12/06/18
 */

public interface OpcionesRepository extends JpaRepository<Opciones, Integer>, JpaSpecificationExecutor<Integer>{

    Iterable<Opciones> findAllByOrderByDescripcionAsc();

}
