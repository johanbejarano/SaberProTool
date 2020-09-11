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
* @author Zathura Code Generator http://zathuracode.org/
* www.zathuracode.org
*
*/
public class DetallePruebaUsuarioDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(DetallePruebaUsuarioDTO.class);
    private Integer dpruId;
    private String estadoRegistro;
    private Date fechaCreacion;
    private Date fechaModificacion;
    private Long usuCreador;
    private Long usuModificador;
    private Integer pregId;
    private Integer prusId;
    private Integer respId;
    private String nombreModulo;
    private Long prioridadModulo;
    private String descripcionPregunta;
    private String retroalimentacionPregunta;
    private List<RespuestaDTO> respuestas;

    public Integer getDpruId() {
        return dpruId;
    }

    public void setDpruId(Integer dpruId) {
        this.dpruId = dpruId;
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

    public Integer getPregId() {
        return pregId;
    }

    public void setPregId(Integer pregId) {
        this.pregId = pregId;
    }

    public Integer getPrusId() {
        return prusId;
    }

    public void setPrusId(Integer prusId) {
        this.prusId = prusId;
    }

    public Integer getRespId() {
        return respId;
    }

    public void setRespId(Integer respId) {
        this.respId = respId;
    }

    public String getDescripcionPregunta() {
        return descripcionPregunta;
    }

    public void setDescripcionPregunta(String descripcionPregunta) {
        this.descripcionPregunta = descripcionPregunta;
    }

    public String getRetroalimentacionPregunta() {
        return retroalimentacionPregunta;
    }

    public void setRetroalimentacionPregunta(String retroalimentacionPregunta) {
        this.retroalimentacionPregunta = retroalimentacionPregunta;
    }

    public String getNombreModulo() {
        return nombreModulo;
    }

    public void setNombreModulo(String nombreModulo) {
        this.nombreModulo = nombreModulo;
    }

    public List<RespuestaDTO> getRespuestas() {
        return respuestas;
    }

    public void setRespuestas(List<RespuestaDTO> respuestas) {
        this.respuestas = respuestas;
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

	public Long getPrioridadModulo() {
		return prioridadModulo;
	}

	public void setPrioridadModulo(Long prioridadModulo) {
		this.prioridadModulo = prioridadModulo;
	}
}
