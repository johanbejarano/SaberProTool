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
@Table(name = "prueba_usuario", schema = "public")
public class PruebaUsuario implements java.io.Serializable {
//    @NotNull
    private Integer prusId;
    @NotNull
    private EstadoPrueba estadoPrueba;
    @NotNull
    private Prueba prueba;
    @NotNull
    private Usuario usuario;
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
    private List<DetallePruebaUsuario> detallePruebaUsuarios = new ArrayList<DetallePruebaUsuario>(0);

    public PruebaUsuario() {
    }

    public PruebaUsuario(Integer prusId,
        List<DetallePruebaUsuario> detallePruebaUsuarios,
        EstadoPrueba estadoPrueba, String estadoRegistro, Date fechaCreacion,
        Date fechaModificacion, Prueba prueba, Long usuCreador,
        Long usuModificador, Usuario usuario) {
        this.prusId = prusId;
        this.estadoPrueba = estadoPrueba;
        this.prueba = prueba;
        this.usuario = usuario;
        this.estadoRegistro = estadoRegistro;
        this.fechaCreacion = fechaCreacion;
        this.fechaModificacion = fechaModificacion;
        this.usuCreador = usuCreador;
        this.usuModificador = usuModificador;
        this.detallePruebaUsuarios = detallePruebaUsuarios;
    }

    @Id
    @Column(name = "prus_id", unique = true, nullable = false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public Integer getPrusId() {
        return this.prusId;
    }

    public void setPrusId(Integer prusId) {
        this.prusId = prusId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "espr_id")
    public EstadoPrueba getEstadoPrueba() {
        return this.estadoPrueba;
    }

    public void setEstadoPrueba(EstadoPrueba estadoPrueba) {
        this.estadoPrueba = estadoPrueba;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "prue_id")
    public Prueba getPrueba() {
        return this.prueba;
    }

    public void setPrueba(Prueba prueba) {
        this.prueba = prueba;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usua_id")
    public Usuario getUsuario() {
        return this.usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "pruebaUsuario")
    public List<DetallePruebaUsuario> getDetallePruebaUsuarios() {
        return this.detallePruebaUsuarios;
    }

    public void setDetallePruebaUsuarios(
        List<DetallePruebaUsuario> detallePruebaUsuarios) {
        this.detallePruebaUsuarios = detallePruebaUsuarios;
    }
}
