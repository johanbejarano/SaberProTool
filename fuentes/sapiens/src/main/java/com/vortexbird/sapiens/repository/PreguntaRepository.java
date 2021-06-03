package com.vortexbird.sapiens.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.vortexbird.sapiens.domain.Pregunta;
import com.vortexbird.sapiens.dto.PreguntaDTO;


/**
* Interface for   PreguntaRepository.
*
*/
public interface PreguntaRepository extends JpaRepository<Pregunta, Integer> {

	List<Pregunta> findByModulo_moduIdAndEstadoRegistro(Integer moduId, String estado);
	
	List<Pregunta> findByEstadoRegistroAndUsuario_usuaId(String estado, Integer usuario);

	List<Pregunta> findByModulo_moduIdAndAndComplejidadAndEstadoRegistro(Integer moduId, Long complejidad,
			String activo);
	
	@Query(nativeQuery = true)
	public Page<PreguntaDTO> getPreguntasPorModulos(
			@Param("pModiId") List<Integer> moduIds, @Param("pFiltro") String filterBy,@Param("pEstado") String estado, Pageable pageable);
	
	@Query(nativeQuery = true)
	public List<Integer> getAllPreguntasPorModulos(
			@Param("pModiId") List<Integer> moduIds, @Param("pFiltro") String filterBy, @Param("pEstado") String estado);
	
}
