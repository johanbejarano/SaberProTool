package com.vortexbird.sapiens.repository;

import com.vortexbird.sapiens.domain.DetallePruebaUsuario;
import com.vortexbird.sapiens.domain.Pregunta;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


/**
* Interface for   DetallePruebaUsuarioRepository.
*
*/
public interface DetallePruebaUsuarioRepository extends JpaRepository<DetallePruebaUsuario, Integer> {
	
	public List<DetallePruebaUsuario> findByPregunta(Pregunta pregunta);
	
	
}

