package com.vortexbird.sapiens.domain;

import java.util.Date;

import javax.persistence.*;

import javax.validation.constraints.*;


/**
* @author Zathura Code Generator http://zathuracode.org/
* www.zathuracode.org
*
*/
@Entity
@Table(name = "prueba_modulo", schema = "public")
public class PruebaModulo implements java.io.Serializable {
    @NotNull
    private Integer prmoId;
    @NotNull
    private Modulo modulo;
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

    public PruebaModulo() {
    }

    public PruebaModulo(Integer prmoId, String estadoRegistro,
        Date fechaCreacion, Date fechaModificacion, Modulo modulo,
        Prueba prueba, Long usuCreador, Long usuModificador) {
        this.prmoId = prmoId;
        this.modulo = modulo;
        this.prueba = prueba;
        this.estadoRegistro = estadoRegistro;
        this.fechaCreacion = fechaCreacion;
        this.fechaModificacion = fechaModificacion;
        this.usuCreador = usuCreador;
        this.usuModificador = usuModificador;
    }

    @Id
    @Column(name = "prmo_id", unique = true, nullable = false)
    public Integer getPrmoId() {
        return this.prmoId;
    }

    public void setPrmoId(Integer prmoId) {
        this.prmoId = prmoId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "modu_id")
    public Modulo getModulo() {
        return this.modulo;
    }

    public void setModulo(Modulo modulo) {
        this.modulo = modulo;
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
