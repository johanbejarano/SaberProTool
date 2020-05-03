package com.vortexbird.sapiens.dto;

import java.io.Serializable;
import java.util.Date;

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
public class PruebaModuloDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(PruebaModuloDTO.class);
    private String estadoRegistro;
    private Date fechaCreacion;
    private Date fechaModificacion;
    private Integer prmoId;
    private Long usuCreador;
    private Long usuModificador;
    private Integer moduId_Modulo;
    private Integer prueId_Prueba;

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

    public Integer getPrmoId() {
        return prmoId;
    }

    public void setPrmoId(Integer prmoId) {
        this.prmoId = prmoId;
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

    public Integer getPrueId_Prueba() {
        return prueId_Prueba;
    }

    public void setPrueId_Prueba(Integer prueId_Prueba) {
        this.prueId_Prueba = prueId_Prueba;
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
