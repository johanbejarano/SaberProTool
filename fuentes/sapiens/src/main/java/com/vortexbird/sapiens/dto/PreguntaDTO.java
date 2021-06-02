package com.vortexbird.sapiens.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Zathura Code Generator http://zathuracode.org/ www.zathuracode.org
 *
 */
public class PreguntaDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final Logger log = LoggerFactory.getLogger(PreguntaDTO.class);
	private String descripcion;
	private String estadoRegistro;
	private Date fechaCreacion;
	private Date fechaModificacion;
	private Integer pregId;
	private String retroalimentacion;
	private Integer usuCreador;
	private Long usuModificador;
	private Integer moduId_Modulo;
	private Integer tprgId_TipoPregunta;
	private Integer timoId;
	private Boolean seleccionMultiple;

	private String nombreModulo;

	private List<RespuestaDTO> respuestasDTO;

	private Long complejidad;
	private Long valorPregunta;
	private Long orden;

	private Integer contId;
	private String nombreContexto;
	private String contexto;
	
	private String codigoUsuario;
	private boolean tienePruebas;

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

	public Integer getPregId() {
		return pregId;
	}

	public void setPregId(Integer pregId) {
		this.pregId = pregId;
	}

	public String getRetroalimentacion() {
		return retroalimentacion;
	}

	public void setRetroalimentacion(String retroalimentacion) {
		this.retroalimentacion = retroalimentacion;
	}

	public Integer getUsuCreador() {
		return usuCreador;
	}

	public void setUsuCreador(Integer usuCreador) {
		this.usuCreador = usuCreador;
	}

	public Long getUsuModificador() {
		return usuModificador;
	}

	public void setUsuModificador(Long usuModificador) {
		this.usuModificador = usuModificador;
	}

	public Integer getModuId_Modulo() {
		return moduId_Modulo;
	}

	public void setModuId_Modulo(Integer moduId_Modulo) {
		this.moduId_Modulo = moduId_Modulo;
	}

	public Integer getTprgId_TipoPregunta() {
		return tprgId_TipoPregunta;
	}

	public void setTprgId_TipoPregunta(Integer tprgId_TipoPregunta) {
		this.tprgId_TipoPregunta = tprgId_TipoPregunta;
	}

	public Integer getTimoId() {
		return timoId;
	}

	public void setTimoId(Integer timoId) {
		this.timoId = timoId;
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

	public List<RespuestaDTO> getRespuestasDTO() {
		return respuestasDTO;
	}

	public void setRespuestasDTO(List<RespuestaDTO> respuestasDTO) {
		this.respuestasDTO = respuestasDTO;
	}

	public String getNombreModulo() {
		return nombreModulo;
	}

	public void setNombreModulo(String nombreModulo) {
		this.nombreModulo = nombreModulo;
	}

	public Long getComplejidad() {
		return complejidad;
	}

	public void setComplejidad(Long complejidad) {
		this.complejidad = complejidad;
	}

	public Long getValorPregunta() {
		return valorPregunta;
	}

	public void setValorPregunta(Long valorPregunta) {
		this.valorPregunta = valorPregunta;
	}

	public Integer getContId() {
		return contId;
	}

	public void setContId(Integer contId) {
		this.contId = contId;
	}

	public String getNombreContexto() {
		return nombreContexto;
	}

	public void setNombreContexto(String nombreContexto) {
		this.nombreContexto = nombreContexto;
	}

	public String getContexto() {
		return contexto;
	}

	public void setContexto(String contexto) {
		this.contexto = contexto;
	}

	public String getCodigoUsuario() {
		return codigoUsuario;
	}

	public void setCodigoUsuario(String codigoUsuario) {
		this.codigoUsuario = codigoUsuario;
	}

	public boolean isTienePruebas() {
		return tienePruebas;
	}

	public void setTienePruebas(boolean tienePruebas) {
		this.tienePruebas = tienePruebas;
	}

	public Long getOrden() {
		return orden;
	}

	public void setOrden(Long orden) {
		this.orden = orden;
	}

	public Boolean getSeleccionMultiple() {
		return seleccionMultiple;
	}

	public void setSeleccionMultiple(Boolean seleccionMultiple) {
		this.seleccionMultiple = seleccionMultiple;
	}
	
	
}
