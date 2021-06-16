package com.vortexbird.sapiens.service;

import java.util.List;

import com.vortexbird.sapiens.domain.DetallePruebaUsuario;
import com.vortexbird.sapiens.domain.Pregunta;
import com.vortexbird.sapiens.dto.DetallePruebaUsuarioDTO;


/**
* @author Zathura Code Generator http://zathuracode.org/
* www.zathuracode.org
*
*/
public interface DetallePruebaUsuarioService extends GenericService<DetallePruebaUsuario, Integer> {

	public List<DetallePruebaUsuario> findByPregunta(Pregunta pregunta);

	Integer getCantidadDeEjecucionesPorModulo(Integer moduId) throws Exception;

	Integer getCantidadDeEjecucionesPorUsuario(Integer usuaId) throws Exception;

	void responder(Integer prusId, Integer respId, String respuestaAbierta, Long usuaId, Boolean seleccionMultiple,
			String ceDesarrolloPrueba, String ceLluviaIdeas,String ceIdeaCentral) throws Exception;

	List<DetallePruebaUsuario> getPreguntasByPruebaUsuario(Integer prusId) throws Exception;

	List<DetallePruebaUsuario> getRespuestasCorrectas(Integer prusId) throws Exception;

	List<DetallePruebaUsuarioDTO> getPreguntasByPruebaUsuarioDTO(Integer prusId) throws Exception;

	Integer getCantidadDeEjecucionesPorPregunta(Integer pregId) throws Exception;
}
