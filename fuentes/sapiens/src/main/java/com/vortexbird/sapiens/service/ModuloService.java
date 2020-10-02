package com.vortexbird.sapiens.service;

import java.util.List;

import com.vortexbird.sapiens.domain.Modulo;
import com.vortexbird.sapiens.dto.ModuloDTO;


/**
* @author Zathura Code Generator http://zathuracode.org/
* www.zathuracode.org
*
*/
public interface ModuloService extends GenericService<Modulo, Integer> {

	List<Modulo> findByTipoModulo(Integer timoId) throws Exception;

	void guardar(ModuloDTO moduloDTO) throws Exception;

	List<Modulo> findByPrograma(Integer progId) throws Exception;

	List<Modulo> findActive() throws Exception;
}
