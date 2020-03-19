package com.vortexbird.sapiens.repository;

import com.vortexbird.sapiens.domain.Usuario;
import com.vortexbird.sapiens.dto.UsuarioDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


/**
* Interface for   UsuarioRepository.
*
*/
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
	public List<Usuario> findByCodigo(String codigo);
	
	@Query(nativeQuery = true)
	public Page<UsuarioDTO> getUsuariosPorTipo(
			@Param("pTiusId") Integer tiusId, @Param("pFiltro") String filterBy, Pageable pageable);
	
}
