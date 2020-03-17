package com.vortexbird.sapiens.repository;

import com.vortexbird.sapiens.domain.Pregunta;
import com.vortexbird.sapiens.domain.Respuesta;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


/**
* Interface for   RespuestaRepository.
*
*/
public interface RespuestaRepository extends JpaRepository<Respuesta, Integer> {
	
	List<Respuesta> findByPregunta(Pregunta pregunta);
	
}
