package com.vortexbird.sapiens.repository;

import com.vortexbird.sapiens.domain.DetallePruebaUsuario;
import com.vortexbird.sapiens.domain.Pregunta;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


/**
* Interface for   DetallePruebaUsuarioRepository.
*
*/
public interface DetallePruebaUsuarioRepository extends JpaRepository<DetallePruebaUsuario, Integer> {
	
	public List<DetallePruebaUsuario> findByPregunta(Pregunta pregunta);
	
	@Query(nativeQuery = true)
	public Integer cantidadEjecucionesPorModulo(
			@Param("pModuId") Integer moduId);
	
	@Query(nativeQuery = true)
	public Integer cantidadEjecucionesPorUsuario(
			@Param("pUsuaId") Integer moduId);
}

