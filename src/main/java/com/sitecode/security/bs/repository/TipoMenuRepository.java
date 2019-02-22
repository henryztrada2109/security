package com.sitecode.security.bs.repository;

import com.sitecode.security.bo.TipoMenu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author Henry Hernandez
 * @version 1.0.0
 * @since 1.0.0
 * created on 1/06/18
 */

public interface TipoMenuRepository extends JpaRepository<TipoMenu, Integer>, JpaSpecificationExecutor<Integer>{

    TipoMenu findByCodigo(Integer codigo);

    Page<TipoMenu> findAllByOrderByCodigoAsc(Pageable page);

    Iterable<TipoMenu> findAllByOrderByCodigoAsc();
}
