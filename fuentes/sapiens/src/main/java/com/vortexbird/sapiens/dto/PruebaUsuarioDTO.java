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
* @author Zathura Code Generator http://zathuracode.org/
* www.zathuracode.org
*
*/
public class PruebaUsuarioDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(PruebaUsuarioDTO.class);
    private String estadoRegistro;
    private Date fechaCreacion;
    private Date fechaModificacion;
    private Integer prusId;
    private Long usuCreador;
    private Long usuModificador;
    private Integer esprId_EstadoPrueba;
    private Integer prueId_Prueba;
    private Integer usuaId_Usuario;

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

    public Integer getPrusId() {
        return prusId;
    }

    public void setPrusId(Integer prusId) {
        this.prusId = prusId;
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

    public Integer getEsprId_EstadoPrueba() {
        return esprId_EstadoPrueba;
    }

    public void setEsprId_EstadoPrueba(Integer esprId_EstadoPrueba) {
        this.esprId_EstadoPrueba = esprId_EstadoPrueba;
    }

    public Integer getPrueId_Prueba() {
        return prueId_Prueba;
    }

    public void setPrueId_Prueba(Integer prueId_Prueba) {
        this.prueId_Prueba = prueId_Prueba;
    }

    public Integer getUsuaId_Usuario() {
        return usuaId_Usuario;
    }

    public void setUsuaId_Usuario(Integer usuaId_Usuario) {
        this.usuaId_Usuario = usuaId_Usuario;
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
