package com.vortexbird.sapiens.service;

import java.util.List;

import com.vortexbird.sapiens.domain.Modulo;


/**
* @author Zathura Code Generator http://zathuracode.org/
* www.zathuracode.org
*
*/
public interface ModuloService extends GenericService<Modulo, Integer> {

	List<Modulo> findByTipoModulo(Integer timoId) throws Exception;
}
