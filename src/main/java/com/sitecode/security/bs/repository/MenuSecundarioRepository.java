package com.sitecode.security.bs.repository;

import com.sitecode.security.bo.MenuSecundario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author Henry Hernandez
 * @version 1.0.0
 * @since 1.0.0
 * created on 22/06/18
 */

public interface MenuSecundarioRepository extends JpaRepository<MenuSecundario, Integer>, JpaSpecificationExecutor<Integer>{

    Page<MenuSecundario> findByIdMenuPrincipalOrderByOrden(Pageable page, Integer menuPrincipal);

    Iterable<MenuSecundario> findByIdMenuPrincipalOrderByOrden(Integer menuPrincipal);

}
