package com.sitecode.security.bs.repository;

import com.sitecode.security.bo.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * @author Henry Hernandez
 * @version 1.0.0
 * @since 1.0.0
 * created on 1/06/18
 */

public interface UsuarioRepository extends JpaRepository<Usuario, Integer>, JpaSpecificationExecutor<Integer>{

    Usuario findByUsuarioAppAndPassAndEstado(String usuarioApp, String pass, Integer estado);

    @Query(value = "FROM Usuario S " +
            "WHERE S.usuarioApp LIKE CONCAT('%',:usuario,'%') AND S.estado = :estado " +
            "ORDER BY S.usuarioApp ASC",
            countQuery = "SELECT COUNT(S) FROM Usuario S " +
                    "WHERE S.usuarioApp LIKE CONCAT('%',:usuario,'%') AND S.estado = :estado " +
                    "ORDER BY S.usuarioApp ASC",
            nativeQuery = false)
    Page<Usuario> filtroPorUsuario(@Param("usuario") String usuario,
                                   @Param("estado") Integer estado,
                                   Pageable page);

    @Query(value = "FROM Usuario S " +
            "WHERE S.idRole = :rol AND S.estado = :estado " +
            "ORDER BY S.usuarioApp ASC",
            countQuery = "SELECT COUNT(0) FROM Usuario S " +
                    "WHERE S.idRole = :rol AND S.estado = :estado " +
                    "ORDER BY S.usuarioApp ASC",
            nativeQuery = false)
    Page<Usuario> filtroPorRol(@Param("rol") Integer rol,
                               @Param("estado") Integer estado,
                               Pageable page);

    @Query(value = "FROM Usuario S " +
            "WHERE S.usuarioApp LIKE CONCAT('%',:usuario,'%') " +
            "AND S.idRole = :rol AND S.estado = :estado " +
            "ORDER BY S.usuarioApp ASC",
            countQuery = "SELECT COUNT(S) FROM Usuario S " +
                    "WHERE S.usuarioApp LIKE CONCAT('%',:usuario,'%') " +
                    "AND S.idRole = :rol AND S.estado = :estado " +
                    "ORDER BY S.usuarioApp ASC",
            nativeQuery = false)
    Page<Usuario> filtroPorUsuarioAndRol(@Param("usuario") String usuario,
                                         @Param("rol") Integer rol,
                                         @Param("estado") Integer estado,
                                         Pageable page);

    Page<Usuario> findAllByOrderByUsuarioAppAsc(Pageable page);

    Page<Usuario> findAllByEstadoOrderByUsuarioAppAsc(Integer estado, Pageable page);

    Usuario findByUsuarioAppAndEstado(String usuario, Integer estado);

}
