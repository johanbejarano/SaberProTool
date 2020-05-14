package com.vortexbird.sapiens.repository;

import java.util.List;

import com.vortexbird.sapiens.domain.Usuario;
import com.vortexbird.sapiens.dto.UsuarioDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


/**
* Interface for   UsuarioRepository.
*
*/
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
	public List<Usuario> findByCodigo(String codigo);
	public List<Usuario> findByIdentificacion(Long identificacion);
	
	@Query(nativeQuery = true)
	public Page<UsuarioDTO> getUsuariosPorTipo(
			@Param("pTiusId") Integer tiusId, @Param("pFiltro") String filterBy, Pageable pageable);
	
}
