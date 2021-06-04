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
public class PruebaDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(PruebaDTO.class);
    private String estadoRegistro;
    private Date fechaCreacion;
    private Date fechaFinal;
    private Date fechaInicial;
    private Date fechaModificacion;
    private Integer prueId;
    private Long tiempo;
    private Long usuCreador;
    private Long usuModificador;
    private Integer tiprId_TipoPrueba;
    
    private String nombreTipoPrueba;
    private String nombrePropietario;
    
    private List<Integer> idModulos;
    private List<Integer> idUsuarios;
    private List<Integer> idPreguntas;

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

    public Date getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(Date fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    public Date getFechaInicial() {
        return fechaInicial;
    }

    public void setFechaInicial(Date fechaInicial) {
        this.fechaInicial = fechaInicial;
    }

    public Date getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public Integer getPrueId() {
        return prueId;
    }

    public void setPrueId(Integer prueId) {
        this.prueId = prueId;
    }

    public Long getTiempo() {
        return tiempo;
    }

    public void setTiempo(Long tiempo) {
        this.tiempo = tiempo;
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

    public Integer getTiprId_TipoPrueba() {
        return tiprId_TipoPrueba;
    }

    public void setTiprId_TipoPrueba(Integer tiprId_TipoPrueba) {
        this.tiprId_TipoPrueba = tiprId_TipoPrueba;
    }
    
    public List<Integer> getIdModulos() {
		return idModulos;
	}

	public void setIdModulos(List<Integer> idModulos) {
		this.idModulos = idModulos;
	}

	public List<Integer> getIdUsuarios() {
		return idUsuarios;
	}

	public void setIdUsuarios(List<Integer> idUsuarios) {
		this.idUsuarios = idUsuarios;
	}
	
	public List<Integer> getIdPreguntas() {
		return idPreguntas;
	}

	public void setIdPreguntas(List<Integer> idPreguntas) {
		this.idPreguntas = idPreguntas;
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

	public String getNombreTipoPrueba() {
		return nombreTipoPrueba;
	}

	public void setNombreTipoPrueba(String nombreTipoPrueba) {
		this.nombreTipoPrueba = nombreTipoPrueba;
	}

	public String getNombrePropietario() {
		return nombrePropietario;
	}

	public void setNombrePropietario(String nombrePropietario) {
		this.nombrePropietario = nombrePropietario;
	}
	
}
