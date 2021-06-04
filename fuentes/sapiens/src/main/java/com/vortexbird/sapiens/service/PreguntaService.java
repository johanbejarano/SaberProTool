package com.vortexbird.sapiens.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.vortexbird.sapiens.domain.Pregunta;
import com.vortexbird.sapiens.dto.CargueMasivoDTO;
import com.vortexbird.sapiens.dto.GuardarPreguntaDTO;
import com.vortexbird.sapiens.dto.PreguntaDTO;


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

	List<Pregunta> getPreguntasPorModulo(Integer moduId) throws Exception;
	
	List<Pregunta> getPreguntasPorModuloPorComplejidad(Integer moduId, Long complejidad) throws Exception;

	void cargar(CargueMasivoDTO request) throws Exception;
	
	Page<PreguntaDTO> getPreguntasPorModulos(List<Integer> ModiIds, String filtro, int pageNumber, int pageSize)
			throws Exception;

	List<Integer> getAllPreguntasPorModulos(List<Integer> moduIds, String filtro) throws Exception;
}
