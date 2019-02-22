package com.sitecode.security.bs.repository;

import com.sitecode.security.bo.Role;
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

public interface RoleRepository extends JpaRepository<Role, Integer>, JpaSpecificationExecutor<Integer>{

    Role findByCodigo(Integer codigo);

    Page<Role> findAllByOrderByCodigoAsc(Pageable page);

    Iterable<Role> findAllByOrderByCodigoAsc();

}
