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
@Table(name = "detalle_prueba_usuario_respuesta", schema = "public")
public class DetallePruebaUsuarioRespuesta implements java.io.Serializable {
//    @NotNull
    private Long dpurId;
    @NotNull
    private DetallePruebaUsuario detallePruebaUsuario;
    @NotNull
    private Respuesta respuesta;
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

    public DetallePruebaUsuarioRespuesta() {
    }

    public DetallePruebaUsuarioRespuesta(Long dpurId,
        DetallePruebaUsuario detallePruebaUsuario, String estadoRegistro,
        Date fechaCreacion, Date fechaModificacion, Respuesta respuesta,
        Long usuCreador, Long usuModificador) {
        this.dpurId = dpurId;
        this.detallePruebaUsuario = detallePruebaUsuario;
        this.respuesta = respuesta;
        this.estadoRegistro = estadoRegistro;
        this.fechaCreacion = fechaCreacion;
        this.fechaModificacion = fechaModificacion;
        this.usuCreador = usuCreador;
        this.usuModificador = usuModificador;
    }

    @Id
    @Column(name = "dpur_id", unique = true, nullable = false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public Long getDpurId() {
        return this.dpurId;
    }

    public void setDpurId(Long dpurId) {
        this.dpurId = dpurId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dpru_id")
    public DetallePruebaUsuario getDetallePruebaUsuario() {
        return this.detallePruebaUsuario;
    }

    public void setDetallePruebaUsuario(
        DetallePruebaUsuario detallePruebaUsuario) {
        this.detallePruebaUsuario = detallePruebaUsuario;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "resp_id")
    public Respuesta getRespuesta() {
        return this.respuesta;
    }

    public void setRespuesta(Respuesta respuesta) {
        this.respuesta = respuesta;
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
