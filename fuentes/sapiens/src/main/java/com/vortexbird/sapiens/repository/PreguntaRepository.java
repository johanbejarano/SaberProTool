package com.vortexbird.sapiens.repository;

import java.util.List;

import com.vortexbird.sapiens.domain.Pregunta;

import org.springframework.data.jpa.repository.JpaRepository;


/**
* Interface for   PreguntaRepository.
*
*/
public interface PreguntaRepository extends JpaRepository<Pregunta, Integer> {

	List<Pregunta> findByModulo_moduIdAndEstadoRegistro(Integer moduId, String estado);
	
	List<Pregunta> findByEstadoRegistroAndUsuCreador(String estado, Long usuario);
}
