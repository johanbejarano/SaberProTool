package com.vortexbird.sapiens.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.OneToMany;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.SqlResultSetMappings;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.vortexbird.sapiens.dto.PruebaUsuarioDTO;

@NamedNativeQueries({
        @NamedNativeQuery(name = "PruebaUsuario.findByUsuario", query = "", resultSetMapping = "PruebaUsuarioDTO"), })
@SqlResultSetMappings({ @SqlResultSetMapping(name = "PruebaUsuarioDTO", classes = {
        @ConstructorResult(targetClass = PruebaUsuarioDTO.class, columns = {
                @ColumnResult(name = "prusId", type = Integer.class),
                @ColumnResult(name = "prueId", type = Integer.class),
                @ColumnResult(name = "tipoPrueba", type = String.class),
                @ColumnResult(name = "fechaInicial", type = Date.class),
                @ColumnResult(name = "fechaFinal", type = Date.class),
                @ColumnResult(name = "tiempoDisponible", type = Long.class),
                @ColumnResult(name = "propietario", type = String.class),
                @ColumnResult(name = "nombreEstadoPrueba", type = String.class), }) }), })
/**
 * @author Zathura Code Generator http://zathuracode.org/ www.zathuracode.org
 *
 */
@Entity
@Table(name = "prueba_usuario", schema = "public")
public class PruebaUsuario implements java.io.Serializable {
    // @NotNull
    private Integer prusId;
    @NotNull
    private EstadoPrueba estadoPrueba;
    @NotNull
    private Prueba prueba;
    @NotNull
    private Usuario usuario;
    private Long tiempoDisponible;
    private Date fechaInicio;
    private Long totalPreguntas;
    private Long totalRespuestasCorrectas;
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

    public PruebaUsuario(Integer prusId, List<DetallePruebaUsuario> detallePruebaUsuarios, EstadoPrueba estadoPrueba,
            String estadoRegistro, Date fechaCreacion, Date fechaModificacion, Prueba prueba, Long usuCreador,
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @Column(name = "fecha_inicio")
    public Date getFechaInicio() {
        return this.fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    @Column(name = "tiempo_disponible")
    public Long getTiempoDisponible() {
        return this.tiempoDisponible;
    }

    public void setTiempoDisponible(Long tiempoDisponible) {
        this.tiempoDisponible = tiempoDisponible;
    }

    @Column(name = "total_preguntas")
    public Long getTotalPreguntas() {
        return this.totalPreguntas;
    }

    public void setTotalPreguntas(Long totalPreguntas) {
        this.totalPreguntas = totalPreguntas;
    }

    @Column(name = "total_respuestas_correctas")
    public Long getTotalRespuestasCorrectas() {
        return this.totalRespuestasCorrectas;
    }

    public void setTotalRespuestasCorrectas(Long totalRespuestasCorrectas) {
        this.totalRespuestasCorrectas = totalRespuestasCorrectas;
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

    public void setDetallePruebaUsuarios(List<DetallePruebaUsuario> detallePruebaUsuarios) {
        this.detallePruebaUsuarios = detallePruebaUsuarios;
    }
}
