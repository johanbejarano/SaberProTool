package com.vortexbird.sapiens.dto;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Zathura Code Generator http://zathuracode.org/ www.zathuracode.org
 *
 */
public class ModuloDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final Logger log = LoggerFactory.getLogger(ModuloDTO.class);
	private Long cantidadPreguntas1;
	private Long cantidadPreguntas2;
	private Long cantidadPreguntas3;
	private Long cantidadPreguntas4;
	private String descripcion;
	private String estadoRegistro;
	private Date fechaCreacion;
	private Date fechaModificacion;
	private Integer moduId;
	private String nombre;
	private Long prioridad;
	private Long usuCreador;
	private Long usuModificador;
	private Integer timoId_TipoModulo;

	private String igualValor;

	public Long getCantidadPreguntas1() {
		return cantidadPreguntas1;
	}

	public void setCantidadPreguntas1(Long cantidadPreguntas1) {
		this.cantidadPreguntas1 = cantidadPreguntas1;
	}

	public Long getCantidadPreguntas2() {
		return cantidadPreguntas2;
	}

	public void setCantidadPreguntas2(Long cantidadPreguntas2) {
		this.cantidadPreguntas2 = cantidadPreguntas2;
	}

	public Long getCantidadPreguntas3() {
		return cantidadPreguntas3;
	}

	public void setCantidadPreguntas3(Long cantidadPreguntas3) {
		this.cantidadPreguntas3 = cantidadPreguntas3;
	}

	public Long getCantidadPreguntas4() {
		return cantidadPreguntas4;
	}

	public void setCantidadPreguntas4(Long cantidadPreguntas4) {
		this.cantidadPreguntas4 = cantidadPreguntas4;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getEstadoRegistro() {
		return estadoRegistro;
	}

	public void setEstadoRegistro(String estadoRegistro) {
		this.estadoRegistro = estadoRegistro;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Date getFechaModificacion() {
		return fechaModificacion;
	}

	public void setFechaModificacion(Date fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}

	public Integer getModuId() {
		return moduId;
	}

	public void setModuId(Integer moduId) {
		this.moduId = moduId;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Long getPrioridad() {
		return prioridad;
	}

	public void setPrioridad(Long prioridad) {
		this.prioridad = prioridad;
	}

	public Long getUsuCreador() {
		return usuCreador;
	}

	public void setUsuCreador(Long usuCreador) {
		this.usuCreador = usuCreador;
	}

	public Long getUsuModificador() {
		return usuModificador;
	}

	public void setUsuModificador(Long usuModificador) {
		this.usuModificador = usuModificador;
	}

	public Integer getTimoId_TipoModulo() {
		return timoId_TipoModulo;
	}

	public void setTimoId_TipoModulo(Integer timoId_TipoModulo) {
		this.timoId_TipoModulo = timoId_TipoModulo;
	}

	@Override
	public String toString() {
		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(SerializationFeature.INDENT_OUTPUT);

		try {
			return mapper.writeValueAsString(this);
		} catch (JsonProcessingException e) {
			log.error(e.getMessage());

			return super.toString();
		}
	}

	public String getIgualValor() {
		return igualValor;
	}

	public void setIgualValor(String igualValor) {
		this.igualValor = igualValor;
	}
}
