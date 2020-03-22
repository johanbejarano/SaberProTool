package com.vortexbird.sapiens.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import javax.validation.constraints.*;


/**
* @author Zathura Code Generator http://zathuracode.org/
* www.zathuracode.org
*
*/
@Entity
@Table(name = "estado_prueba", schema = "public")
public class EstadoPrueba implements java.io.Serializable {
//    @NotNull
    private Integer esprId;
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
    private List<PruebaUsuario> pruebaUsuarios = new ArrayList<PruebaUsuario>(0);

    public EstadoPrueba() {
    }

    public EstadoPrueba(Integer esprId, String estadoRegistro,
        Date fechaCreacion, Date fechaModificacion,
        List<PruebaUsuario> pruebaUsuarios, Long usuCreador, Long usuModificador) {
        this.esprId = esprId;
        this.estadoRegistro = estadoRegistro;
        this.fechaCreacion = fechaCreacion;
        this.fechaModificacion = fechaModificacion;
        this.usuCreador = usuCreador;
        this.usuModificador = usuModificador;
        this.pruebaUsuarios = pruebaUsuarios;
    }

    @Id
    @Column(name = "espr_id", unique = true, nullable = false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public Integer getEsprId() {
        return this.esprId;
    }

    public void setEsprId(Integer esprId) {
        this.esprId = esprId;
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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "estadoPrueba")
    public List<PruebaUsuario> getPruebaUsuarios() {
        return this.pruebaUsuarios;
    }

    public void setPruebaUsuarios(List<PruebaUsuario> pruebaUsuarios) {
        this.pruebaUsuarios = pruebaUsuarios;
    }
}
