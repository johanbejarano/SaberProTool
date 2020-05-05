package com.vortexbird.sapiens.service;

import com.vortexbird.sapiens.domain.Usuario;
import com.vortexbird.sapiens.dto.UsuarioDTO;

import org.springframework.data.domain.Page;


/**
* @author Zathura Code Generator http://zathuracode.org/
* www.zathuracode.org
*
*/
public interface UsuarioService extends GenericService<Usuario, Integer> {

	UsuarioDTO login(String codigo, String password) throws Exception;

	public Page<UsuarioDTO> getUsuariosPorTipo(Integer tiusId, String filtro, int pageNumber, int pageSize) throws Exception;

	String getNombreUsuario(Integer usuaId) throws Exception;

	void guardar(UsuarioDTO usuarioDTO) throws Exception;
}
