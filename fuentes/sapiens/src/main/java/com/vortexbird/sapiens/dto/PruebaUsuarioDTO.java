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
    private Integer prueId;
    private Integer usuaId_Usuario;

    private Long totalPreguntas;
    private Long totalRespuestasCorrectas;

    private String tipoPrueba;
    private Date fechaInicial;
    private Date fechaFinal;
    private Date fechaInicio;
    private Long tiempoDisponible;
    private String propietario;
    private String nombreEstadoPrueba;

    public PruebaUsuarioDTO() {
        super();
    }

    public PruebaUsuarioDTO(Integer prusId, Integer prueId, String tipoPrueba, Date fechaInicial, Date fechaFinal,
            Date fechaInicio, Long tiempoDisponible, String propietario, String nombreEstadoPrueba, Long totalPreguntas, Long totalRespuestasCorrectas) {
        this.prusId = prusId;
        this.prueId = prueId;
        this.tipoPrueba = tipoPrueba;
        this.fechaInicial = fechaInicial;
        this.fechaFinal = fechaFinal;
        this.fechaInicio = fechaInicio;
        this.tiempoDisponible = tiempoDisponible;
        this.propietario = propietario;
        this.nombreEstadoPrueba = nombreEstadoPrueba;
        this.totalPreguntas = totalPreguntas;
        this.totalRespuestasCorrectas = totalRespuestasCorrectas;
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

    public Integer getPrueId() {
        return prueId;
    }

    public void setPrueId(Integer prueId) {
        this.prueId = prueId;
    }

    public Integer getUsuaId_Usuario() {
        return usuaId_Usuario;
    }

    public void setUsuaId_Usuario(Integer usuaId_Usuario) {
        this.usuaId_Usuario = usuaId_Usuario;
    }

    public String getTipoPrueba() {
        return tipoPrueba;
    }

    public void setTipoPrueba(String tipoPrueba) {
        this.tipoPrueba = tipoPrueba;
    }

    public Date getFechaInicial() {
        return fechaInicial;
    }

    public void setFechaInicial(Date fechaInicial) {
        this.fechaInicial = fechaInicial;
    }

    public Date getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(Date fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Long getTiempoDisponible() {
        return tiempoDisponible;
    }

    public void setTiempoDisponible(Long tiempoDisponible) {
        this.tiempoDisponible = tiempoDisponible;
    }

    public String getPropietario() {
        return propietario;
    }

    public void setPropietario(String propietario) {
        this.propietario = propietario;
    }

    public String getNombreEstadoPrueba() {
        return nombreEstadoPrueba;
    }

    public void setNombreEstadoPrueba(String nombreEstadoPrueba) {
        this.nombreEstadoPrueba = nombreEstadoPrueba;
    }

    public Long getTotalPreguntas() {
        return this.totalPreguntas;
    }

    public void setTotalPreguntas(Long totalPreguntas) {
        this.totalPreguntas = totalPreguntas;
    }

    public Long getTotalRespuestasCorrectas() {
        return this.totalRespuestasCorrectas;
    }

    public void setTotalRespuestasCorrectas(Long totalRespuestasCorrectas) {
        this.totalRespuestasCorrectas = totalRespuestasCorrectas;
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
