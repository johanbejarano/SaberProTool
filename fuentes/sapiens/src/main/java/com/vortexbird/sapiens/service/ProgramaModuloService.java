package com.vortexbird.sapiens.service;

import java.util.List;

import com.vortexbird.sapiens.domain.ProgramaModulo;


/**
* @author Zathura Code Generator http://zathuracode.org/
* www.zathuracode.org
*
*/
public interface ProgramaModuloService extends GenericService<ProgramaModulo, Integer> {

	List<ProgramaModulo> findByPrograma(Integer progId);
}
