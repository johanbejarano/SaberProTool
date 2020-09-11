package com.vortexbird.sapiens.service;

import java.util.List;

import com.vortexbird.sapiens.domain.Contexto;
import com.vortexbird.sapiens.dto.ContextoDTO;


/**
* @author Zathura Code Generator http://zathuracode.org/
* www.zathuracode.org
*
*/
public interface ContextoService extends GenericService<Contexto, Integer> {

	List<ContextoDTO> getByModulo(Integer moduId) throws Exception;

	void guardar(ContextoDTO contextoDTO) throws Exception;
}
