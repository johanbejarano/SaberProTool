package com.vortexbird.sapiens.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.SqlResultSetMappings;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


/**
* @author Zathura Code Generator http://zathuracode.org/
* www.zathuracode.org
*
*/
@NamedNativeQueries({
	@NamedNativeQuery(name = "DetallePruebaUsuario.cantidadEjecucionesPorModulo", query = "", resultSetMapping = "DetallePruebaUsuarioCount"),
	@NamedNativeQuery(name = "DetallePruebaUsuario.cantidadEjecucionesPorUsuario", query = "", resultSetMapping = "DetallePruebaUsuarioCount"),
	@NamedNativeQuery(name = "DetallePruebaUsuario.cantidadEjecucionesPorPregunta", query = "", resultSetMapping = "DetallePruebaUsuarioCount"),
})
@SqlResultSetMappings({
	@SqlResultSetMapping(name = "DetallePruebaUsuarioCount", columns = {
			@ColumnResult(name = "total", type = Integer.class) }), })
@Entity
@Table(name = "detalle_prueba_usuario", schema = "public")
public class DetallePruebaUsuario implements java.io.Serializable {
    //@NotNull
    private Integer dpruId;
    @NotNull
    private Pregunta pregunta;
    @NotNull
    private PruebaUsuario pruebaUsuario;
    //@NotNull
    private Respuesta respuesta;
    @Size(max = 25500)
    private String respuestaAbierta;
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

    public DetallePruebaUsuario() {
    }

    public DetallePruebaUsuario(Integer dpruId, String estadoRegistro,
        Date fechaCreacion, Date fechaModificacion, Pregunta pregunta,
        PruebaUsuario pruebaUsuario, Respuesta respuesta, Long usuCreador,
        Long usuModificador) {
        this.dpruId = dpruId;
        this.pregunta = pregunta;
        this.pruebaUsuario = pruebaUsuario;
        this.respuesta = respuesta;
        this.estadoRegistro = estadoRegistro;
        this.fechaCreacion = fechaCreacion;
        this.fechaModificacion = fechaModificacion;
        this.usuCreador = usuCreador;
        this.usuModificador = usuModificador;
    }

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "dpru_id", unique = true, nullable = false)
    public Integer getDpruId() {
        return this.dpruId;
    }

    public void setDpruId(Integer dpruId) {
        this.dpruId = dpruId;
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
    @JoinColumn(name = "prus_id")
    public PruebaUsuario getPruebaUsuario() {
        return this.pruebaUsuario;
    }

    public void setPruebaUsuario(PruebaUsuario pruebaUsuario) {
        this.pruebaUsuario = pruebaUsuario;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "resp_id")
    public Respuesta getRespuesta() {
        return this.respuesta;
    }

    public void setRespuesta(Respuesta respuesta) {
        this.respuesta = respuesta;
    }
    
    @Column(name = "respuesta_abierta")
    public String getRespuestaAbierta() {
        return this.respuestaAbierta;
    }

    public void setRespuestaAbierta(String respuestaAbierta) {
        this.respuestaAbierta = respuestaAbierta;
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
