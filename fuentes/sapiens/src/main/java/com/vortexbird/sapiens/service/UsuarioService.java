package com.vortexbird.sapiens.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.vortexbird.sapiens.domain.Usuario;
import com.vortexbird.sapiens.dto.CargueMasivoDTO;
import com.vortexbird.sapiens.dto.UsuarioDTO;




/**
* @author Zathura Code Generator http://zathuracode.org/
* www.zathuracode.org
*
*/
public interface UsuarioService extends GenericService<Usuario, Integer> {

	UsuarioDTO login(String codigo, String password) throws Exception;

	public Page<UsuarioDTO> getUsuariosPorTipo(Integer tiusId, Integer semestre, Integer progId, Integer facuId, Integer grupId,String filtro, int pageNumber, int pageSize) throws Exception;
	
	public List<Integer> getAllUsuariosPorTipo(Integer tiusId, Integer semestre, Integer progId, Integer facuId, Integer grupId,String filtro) throws Exception;

	String getNombreUsuario(Integer usuaId) throws Exception;

	void guardar(UsuarioDTO usuarioDTO) throws Exception;

	String solicitarClave(String codigo) throws Exception;

	void recuperarClave(String token) throws Exception;
	
	void cambiarClave(UsuarioDTO usuarioDTO) throws Exception;

	List<String> cargar(CargueMasivoDTO request) throws Exception;

	void recrearClaves(Long usuarioCreador) throws Exception;

	List<UsuarioDTO> getUsuariosPorGrupo(Integer grupId) throws Exception;
	
	String getCorreoUsuarioPorCodigo(String codigo) throws Exception;
}
