package com.vortexbird.sapiens.service;

import com.vortexbird.sapiens.domain.DetallePruebaUsuario;
import com.vortexbird.sapiens.domain.Pregunta;

import java.math.*;

import java.util.*;


/**
* @author Zathura Code Generator http://zathuracode.org/
* www.zathuracode.org
*
*/
public interface DetallePruebaUsuarioService extends GenericService<DetallePruebaUsuario, Integer> {

	public List<DetallePruebaUsuario> findByPregunta(Pregunta pregunta);

	Integer getCantidadDeEjecucionesPorModulo(Integer moduId) throws Exception;

	Integer getCantidadDeEjecucionesPorUsuario(Integer usuaId) throws Exception;
}
