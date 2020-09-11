package com.vortexbird.sapiens.dto;

import java.io.Serializable;
import java.util.List;

/**
 * @author Zathura Code Generator http://zathuracode.org/ www.zathuracode.org
 *
 */
public class CargueMasivoDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	private List<PreguntaDTO> preguntas;
	private List<UsuarioDTO> usuarios;
	private Long usuarioCreador;

	public List<PreguntaDTO> getPreguntas() {
		return preguntas;
	}

	public void setPreguntas(List<PreguntaDTO> preguntas) {
		this.preguntas = preguntas;
	}

	public List<UsuarioDTO> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<UsuarioDTO> usuarios) {
		this.usuarios = usuarios;
	}

	public Long getUsuarioCreador() {
		return usuarioCreador;
	}

	public void setUsuarioCreador(Long usuarioCreador) {
		this.usuarioCreador = usuarioCreador;
	}

}
