package com.vortexbird.sapiens.service;

import com.vortexbird.sapiens.domain.Usuario;
import com.vortexbird.sapiens.dto.UsuarioDTO;

import java.math.*;

import java.util.*;


/**
* @author Zathura Code Generator http://zathuracode.org/
* www.zathuracode.org
*
*/
public interface UsuarioService extends GenericService<Usuario, Integer> {

	UsuarioDTO login(String codigo, String password) throws Exception;
}
