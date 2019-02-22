package com.sitecode.security.bs.repository;

import com.sitecode.security.bo.MenuPrincipal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author Henry Hernandez
 * @version 1.0.0
 * @since 1.0.0
 * created on 12/06/18
 */

public interface MenuPrincipalRepository extends JpaRepository<MenuPrincipal, Integer>, JpaSpecificationExecutor<Integer>{

    Page<MenuPrincipal> findByIdTipoMenuOrderByOrden(Pageable page, Integer tipoMenu);

    Iterable<MenuPrincipal> findByIdTipoMenuOrderByOrden(Integer tipoMenu);

    Iterable<MenuPrincipal> findAllByOrderByOrdenAsc();

}
