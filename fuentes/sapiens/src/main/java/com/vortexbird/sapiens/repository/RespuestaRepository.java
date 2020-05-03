package com.vortexbird.sapiens.repository;

import java.util.List;

import com.vortexbird.sapiens.domain.Pregunta;
import com.vortexbird.sapiens.domain.Respuesta;

import org.springframework.data.jpa.repository.JpaRepository;


/**
* Interface for   RespuestaRepository.
*
*/
public interface RespuestaRepository extends JpaRepository<Respuesta, Integer> {
	
	List<Respuesta> findByPregunta(Pregunta pregunta);
	
}
