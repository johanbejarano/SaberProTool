package com.vortexbird.sapiens.domain;

import java.util.Date;

import javax.persistence.*;

import javax.validation.constraints.*;


/**
* @author Zathura Code Generator http://zathuracode.org
* www.zathuracode.org
*
*/
@Entity
@Table(name = "prueba_pregunta", schema = "public")
public class PruebaPregunta implements java.io.Serializable {
//	@NotNull
    private Long prprId;
    @NotNull
    private Pregunta pregunta;
    @NotNull
    private Prueba prueba;
    @NotNull
    @NotEmpty
    @Size(max = 255)
    private String estadoRegistro;
    @NotNull
    private Date fechaCreacion;
    private Date fechaModificacion;
    @NotNull
    private Long usuCreador;
    private Long usuModificador;

    public PruebaPregunta() {
    }

    public PruebaPregunta(Long prprId, String estadoRegistro,
        Date fechaCreacion, Date fechaModificacion, Pregunta pregunta,
        Prueba prueba, Long usuCreador, Long usuModificador) {
        this.prprId = prprId;
        this.pregunta = pregunta;
        this.prueba = prueba;
        this.estadoRegistro = estadoRegistro;
        this.fechaCreacion = fechaCreacion;
        this.fechaModificacion = fechaModificacion;
        this.usuCreador = usuCreador;
        this.usuModificador = usuModificador;
    }

    @Id
    @Column(name = "prpr_id", unique = true, nullable = false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public Long getPrprId() {
        return this.prprId;
    }

    public void setPrprId(Long prprId) {
        this.prprId = prprId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "preg_id")
    public Pregunta getPregunta() {
        return this.pregunta;
    }

    public void setPregunta(Pregunta pregunta) {
        this.pregunta = pregunta;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "prue_id")
    public Prueba getPrueba() {
        return this.prueba;
    }

    public void setPrueba(Prueba prueba) {
        this.prueba = prueba;
    }

    @Column(name = "estado_registro", nullable = false)
    public String getEstadoRegistro() {
        return this.estadoRegistro;
    }

    public void setEstadoRegistro(String estadoRegistro) {
        this.estadoRegistro = estadoRegistro;
    }

    @Column(name = "fecha_creacion", nullable = false)
    public Date getFechaCreacion() {
        return this.fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    @Column(name = "fecha_modificacion")
    public Date getFechaModificacion() {
        return this.fechaModificacion;
    }

    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    @Column(name = "usu_creador", nullable = false)
    public Long getUsuCreador() {
        return this.usuCreador;
    }

    public void setUsuCreador(Long usuCreador) {
        this.usuCreador = usuCreador;
    }

    @Column(name = "usu_modificador")
    public Long getUsuModificador() {
        return this.usuModificador;
    }

    public void setUsuModificador(Long usuModificador) {
        this.usuModificador = usuModificador;
    }
}
