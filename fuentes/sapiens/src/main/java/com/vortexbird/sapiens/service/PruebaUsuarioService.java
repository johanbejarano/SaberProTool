package com.vortexbird.sapiens.service;

import java.util.List;

import com.vortexbird.sapiens.domain.PruebaUsuario;
import com.vortexbird.sapiens.dto.PruebaUsuarioDTO;


/**
* @author Zathura Code Generator http://zathuracode.org/
* www.zathuracode.org
*
*/
public interface PruebaUsuarioService extends GenericService<PruebaUsuario, Integer> {

    void iniciarPrueba(Integer prusId, Long usuario) throws Exception;

    void pausarPrueba(Integer prusId, Long usuario) throws Exception;
    
	void finalizarPrueba(Integer prusId, Long usuario) throws Exception;

	List<PruebaUsuarioDTO> getPruebas(Long usuaId, Long prusId) throws Exception;
}
