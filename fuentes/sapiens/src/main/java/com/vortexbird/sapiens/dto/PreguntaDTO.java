package com.vortexbird.sapiens.dto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.vortexbird.sapiens.domain.Respuesta;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

import java.sql.*;

import java.util.Date;
import java.util.List;


/**
* @author Zathura Code Generator http://zathuracode.org/
* www.zathuracode.org
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
    private Long usuCreador;
    private Long usuModificador;
    private Integer moduId_Modulo;
    private Integer tprgId_TipoPregunta;
    private Integer timoId;
    
    private String nombreModulo;
    
    private List<RespuestaDTO> respuestasDTO;

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

	
}
