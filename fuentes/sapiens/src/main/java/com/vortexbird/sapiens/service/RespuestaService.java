package com.vortexbird.sapiens.service;

import java.util.List;

import com.vortexbird.sapiens.domain.Respuesta;


/**
* @author Zathura Code Generator http://zathuracode.org/
* www.zathuracode.org
*
*/
public interface RespuestaService extends GenericService<Respuesta, Integer> {

	List<Respuesta> findRespuestasDePregunta(Integer pregId) throws Exception;
}
