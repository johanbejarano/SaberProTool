package com.vortexbird.sapiens.repository;

import java.util.List;

import com.vortexbird.sapiens.domain.PruebaUsuario;
import com.vortexbird.sapiens.dto.PruebaUsuarioDTO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


/**
* Interface for   PruebaUsuarioRepository.
*
*/
public interface PruebaUsuarioRepository extends JpaRepository<PruebaUsuario, Integer> {
    @Query(nativeQuery = true)
    List<PruebaUsuarioDTO> findByUsuario(@Param("pUsuaId") Integer usuaId, @Param("pPrusId") Integer prusId, @Param("pEstado") String estado);
}
