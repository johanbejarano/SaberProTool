package com.vortexbird.sapiens.dto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

import java.sql.*;

import java.util.Date;


/**
* @author Zathura Code Generator http://zathuracode.org
* www.zathuracode.org
*
*/
public class DetallePruebaUsuarioRespuestaDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(DetallePruebaUsuarioRespuestaDTO.class);
    private Long dpurId;
    private String estadoRegistro;
    private Date fechaCreacion;
    private Date fechaModificacion;
    private Long usuCreador;
    private Long usuModificador;
    private Integer dpruId_DetallePruebaUsuario;
    private Integer respId_Respuesta;

    public Long getDpurId() {
        return dpurId;
    }

    public void setDpurId(Long dpurId) {
        this.dpurId = dpurId;
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

    public Integer getDpruId_DetallePruebaUsuario() {
        return dpruId_DetallePruebaUsuario;
    }

    public void setDpruId_DetallePruebaUsuario(
        Integer dpruId_DetallePruebaUsuario) {
        this.dpruId_DetallePruebaUsuario = dpruId_DetallePruebaUsuario;
    }

    public Integer getRespId_Respuesta() {
        return respId_Respuesta;
    }

    public void setRespId_Respuesta(Integer respId_Respuesta) {
        this.respId_Respuesta = respId_Respuesta;
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
}
