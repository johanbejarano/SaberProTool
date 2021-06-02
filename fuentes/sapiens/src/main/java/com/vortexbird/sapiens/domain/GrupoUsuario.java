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
@Table(name = "grupo_usuario", schema = "public")
public class GrupoUsuario implements java.io.Serializable {
    @NotNull
    private Long grusId;
    @NotNull
    private Grupo grupo;
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

    public GrupoUsuario() {
    }

    public GrupoUsuario(Long grusId, String estadoRegistro, Date fechaCreacion,
        Date fechaModificacion, Grupo grupo, Long usuCreador,
        Long usuModificador, Usuario usuario) {
        this.grusId = grusId;
        this.grupo = grupo;
        this.usuario = usuario;
        this.estadoRegistro = estadoRegistro;
        this.fechaCreacion = fechaCreacion;
        this.fechaModificacion = fechaModificacion;
        this.usuCreador = usuCreador;
        this.usuModificador = usuModificador;
    }

    @Id
    @Column(name = "grus_id", unique = true, nullable = false)
    public Long getGrusId() {
        return this.grusId;
    }

    public void setGrusId(Long grusId) {
        this.grusId = grusId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "grup_id")
    public Grupo getGrupo() {
        return this.grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
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
}
