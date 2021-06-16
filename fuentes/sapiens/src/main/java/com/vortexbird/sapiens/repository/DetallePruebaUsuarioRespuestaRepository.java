package com.vortexbird.sapiens.repository;

import com.vortexbird.sapiens.domain.DetallePruebaUsuario;
import com.vortexbird.sapiens.domain.DetallePruebaUsuarioRespuesta;
import com.vortexbird.sapiens.domain.Respuesta;

import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;


/**
* Interface for   DetallePruebaUsuarioRespuestaRepository.
*
*/
public interface DetallePruebaUsuarioRespuestaRepository extends JpaRepository<DetallePruebaUsuarioRespuesta, Long> {

	List<DetallePruebaUsuarioRespuesta> findByDetallePruebaUsuarioAndRespuesta(DetallePruebaUsuario detallePruebaUsuario, Respuesta respuesta);
	List<DetallePruebaUsuarioRespuesta> findByDetallePruebaUsuarioAndRespuestaAndEstadoRegistro(DetallePruebaUsuario detallePruebaUsuario, Respuesta respuesta, String estado);
	
	List<DetallePruebaUsuarioRespuesta> findByDetallePruebaUsuarioAndEstadoRegistro(DetallePruebaUsuario detallePruebaUsuario, String estado);
}
