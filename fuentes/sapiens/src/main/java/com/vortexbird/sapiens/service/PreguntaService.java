package com.vortexbird.sapiens.service;

import com.vortexbird.sapiens.domain.Pregunta;
import com.vortexbird.sapiens.dto.GuardarPreguntaDTO;
import com.vortexbird.sapiens.dto.PreguntaDTO;

import java.math.*;

import java.util.*;


/**
* @author Zathura Code Generator http://zathuracode.org/
* www.zathuracode.org
*
*/
public interface PreguntaService extends GenericService<Pregunta, Integer> {

	Pregunta guardarPregunta(GuardarPreguntaDTO guardarPreguntaDTO) throws Exception;

	Pregunta actualizarPregunta(GuardarPreguntaDTO guardarPreguntaDTO) throws Exception;

	PreguntaDTO getPregunta(Integer pregId) throws Exception;

	List<PreguntaDTO> getPreguntasPorUsuario(Integer usuaId) throws Exception;
}
