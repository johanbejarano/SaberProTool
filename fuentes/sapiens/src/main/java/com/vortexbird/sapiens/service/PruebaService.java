package com.vortexbird.sapiens.service;

import java.util.List;

import com.vortexbird.sapiens.domain.Prueba;
import com.vortexbird.sapiens.dto.PruebaDTO;


/**
* @author Zathura Code Generator http://zathuracode.org/
* www.zathuracode.org
*
*/
public interface PruebaService extends GenericService<Prueba, Integer> {

	List<PruebaDTO> getPruebasDeUsuarioCreador(Long usuCreador) throws Exception;

	PruebaDTO guardarPrueba(PruebaDTO pruebaDTO) throws Exception;

	PruebaDTO getPrueba(Integer prueId) throws Exception;

	PruebaDTO modificarPrueba(PruebaDTO pruebaDTO) throws Exception;
}
