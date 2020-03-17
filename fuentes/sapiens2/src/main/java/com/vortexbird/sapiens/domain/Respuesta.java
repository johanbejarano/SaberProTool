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
@Table(name = "respuesta", schema = "public")
public class Respuesta implements java.io.Serializable {
    @NotNull
    private Integer respId;
    @NotNull
    private Pregunta pregunta;
    @NotNull
    private Long correcta;
    @NotNull
    @NotEmpty
    @Size(max = 255)
    private String descripcion;
    @NotNull
    @NotEmpty
    @Size(max = 255)
    private String estadoRegistro;
    @NotNull
    private Date fechaCreacion;
    private Date fechaModificacion;
    @NotNull
    @NotEmpty
    @Size(max = 255)
    private String retroalimentacion;
    @NotNull
    private Long usuCreador;
    private Long usuModificador;
    private List<DetallePruebaUsuario> detallePruebaUsuarios = new ArrayList<DetallePruebaUsuario>(0);

    public Respuesta() {
    }

    public Respuesta(Integer respId, Long correcta, String descripcion,
        List<DetallePruebaUsuario> detallePruebaUsuarios,
        String estadoRegistro, Date fechaCreacion, Date fechaModificacion,
        Pregunta pregunta, String retroalimentacion, Long usuCreador,
        Long usuModificador) {
        this.respId = respId;
        this.pregunta = pregunta;
        this.correcta = correcta;
        this.descripcion = descripcion;
        this.estadoRegistro = estadoRegistro;
        this.fechaCreacion = fechaCreacion;
        this.fechaModificacion = fechaModificacion;
        this.retroalimentacion = retroalimentacion;
        this.usuCreador = usuCreador;
        this.usuModificador = usuModificador;
        this.detallePruebaUsuarios = detallePruebaUsuarios;
    }

    @Id
    @Column(name = "resp_id", unique = true, nullable = false)
    public Integer getRespId() {
        return this.respId;
    }

    public void setRespId(Integer respId) {
        this.respId = respId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "preg_id")
    public Pregunta getPregunta() {
        return this.pregunta;
    }

    public void setPregunta(Pregunta pregunta) {
        this.pregunta = pregunta;
    }

    @Column(name = "correcta", nullable = false)
    public Long getCorrecta() {
        return this.correcta;
    }

    public void setCorrecta(Long correcta) {
        this.correcta = correcta;
    }

    @Column(name = "descripcion", nullable = false)
    public String getDescripcion() {
        return this.descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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

    @Column(name = "retroalimentacion", nullable = false)
    public String getRetroalimentacion() {
        return this.retroalimentacion;
    }

    public void setRetroalimentacion(String retroalimentacion) {
        this.retroalimentacion = retroalimentacion;
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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "respuesta")
    public List<DetallePruebaUsuario> getDetallePruebaUsuarios() {
        return this.detallePruebaUsuarios;
    }

    public void setDetallePruebaUsuarios(
        List<DetallePruebaUsuario> detallePruebaUsuarios) {
        this.detallePruebaUsuarios = detallePruebaUsuarios;
    }
}
